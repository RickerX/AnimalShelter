package com.example.animalshelter.services;

import com.example.animalshelter.model.*;
import com.example.animalshelter.repository.*;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
@Service
public class ReportService {
    private final ShelterUserRepository shelterUserRepository;
    private final GuardianRepository guardianRepository;
    private final ReportRepository reportRepository;
    private final ProbationPeriodRepository probationPeriodRepository;
    private final AnimalRepository animalRepository;

    public ReportService(ShelterUserRepository shelterUserRepository, GuardianRepository guardianRepository, ReportRepository reportRepository, ProbationPeriodRepository probationPeriodRepository, AnimalRepository animalRepository) {
        this.shelterUserRepository = shelterUserRepository;
        this.guardianRepository = guardianRepository;
        this.reportRepository = reportRepository;
        this.probationPeriodRepository = probationPeriodRepository;
        this.animalRepository = animalRepository;
    }
    public boolean isSendReportCommand(String userMessage) {
        return userMessage.equals("/sendReport");
    }

    public void sendReportFirstStep(Long chatId) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElseThrow();
        if (userIsGuardian(user.getUsername())) {

            Guardian guardian = guardianRepository.findGuardianByUsername(
                    user.getUsername()).orElseThrow();

            if (guardian.getProbationPeriods().isEmpty() ||
                    guardian.getAnimals().isEmpty()) {

                MessageService.sendMessage(chatId, "Что-то пошло не так! За Вами " +
                        "не закреплён питомец и/или не назначен испытательный срок!" +
                        "Свяжитесь, пожалуйста, с волонтёром - /volunteer. " +
                        "Или запросите обратный звонок - /callback. Приносим свои " +
                        "извинения.");
                return;
            }

            if (guardian.getChatId() == null || !guardian.getChatId().equals(chatId)) {
                guardian.setChatId(chatId);
                guardianRepository.save(guardian);
            }

            List<Animal> chosenSheltersAnimals = new ArrayList<>();
            for (Animal a : guardian.getAnimals()) {
                if (a.getShelter().getShelterType() == user.getShelterType()) {
                    chosenSheltersAnimals.add(a);
                }
            }
            if (chosenSheltersAnimals.isEmpty()) {
                MessageService.sendMessage(chatId, "Я вижу, что питомец(ы), которых Вы забрали, - " +
                        "не из выбранного Вами сейчас приюта. Пожалуйста, нажмите /start, " +
                        "чтобы выбрать нужный приют. " +
                        "Если произошла ошибка, свяжитесь, пожалуйста, с волонтёром - /volunteer. " +
                        "Или запросите обратный звонок - /callback");
                return;
            }

            List<Animal> unexpired = new ArrayList<>();
            for (Animal a : chosenSheltersAnimals) {
                if (!a.getProbationPeriod().getEnds().isBefore(LocalDate.now())) {
                    unexpired.add(a);
                }
            }
            if (unexpired.isEmpty()) {
                MessageService.sendMessage(chatId, "Ваши испытательный(е) срок(и) " +
                        "окончены! Вам больше не нужно отправлять отчёты. " +
                        "Если произошла ошибка, свяжитесь, пожалуйста, с волонтёром - /volunteer. " +
                        "Или запросите обратный звонок - /callback");
                return;
            }

            List<String> buttonNames = new ArrayList<>();
            for (Animal a : unexpired) {
                String s = "№" + a.getId() + " " + a.getNickname();
                buttonNames.add(s);
            }

            InlineKeyboardMarkup keyboardMarkup = MenuService.createTwiceButtons(
                    buttonNames.toArray(new String[0])
            );

            MessageService.sendMessage(chatId, "/sendReportFirstStep",
                    "Отлично, приступим!\n" +
                            "Обращаем Ваше внимание, что отчёт нужно заполнять только " +
                            "один раз в день каждый день до 21:00.\n" +
                            "Следуя инструкциям бота, в качестве отчёта Вам необходимо " +
                            "прислать в первом сообщении фото питомца, а во втором - " +
                            "как можно более подробное описание:\n" +
                            "- рациона животного,\n" +
                            "- общего самочувствия и привыкания к новому месту,\n" +
                            "- изменений в поведении: отказ от старых привычек, приобретение " +
                            "новых.\n" +
                            "Сначала выберите, пожалуйста, питомца, о котором собираетесь " +
                            "отправить отчёт.",
                    keyboardMarkup);

            user.setUserStatus(UserStatus.CHOOSING_PET_FOR_REPORT);
            shelterUserRepository.save(user);
        } else {
            MessageService.sendMessage(chatId, "/sendReportFirstStep",
                    "К сожалению, Вы не являетесь усыновителем животного. " +
                            "Пожалуйста, приезжайте в приют с необходимыми документами и выберите питомца. " +
                            "Волонтёр зарегистрирует Вас в системе и Вы сможете отправлять отчёты. " +
                            "Если произошла ошибка, свяжитесь, пожалуйста, с волонтёром - /volunteer. " +
                            "Или запросите обратный звонок - /callback");
        }
    }

    public boolean userIsGuardian(String username) {
        return guardianRepository.findGuardianByUsername(username).isPresent();
    }

    public void sendReportSecondStep(Long chatId, String callbackData) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElseThrow();

        if (!callbackData.startsWith("№")) {
            resetUsersShelterTypeAndCancel(chatId, user);
            return;
        }

        String animalIdString = callbackData.substring(1,
                callbackData.indexOf(" "));
        Long animalId = Long.parseLong(animalIdString);

        Guardian guardian = guardianRepository.findGuardianByUsername(user.getUsername()).orElseThrow();
        ProbationPeriod probPeriod = probationPeriodRepository.findByAnimal_Id(animalId);

        Report report;
        if (reportRepository.existsByProbationPeriodAndDate(
                probPeriod, LocalDate.now())) {
            report = reportRepository.findReportByProbationPeriodAndDate(
                    probPeriod, LocalDate.now()).orElseThrow();
            if (report.getEntry() != null && report.getPhotoId() != null) {
                MessageService.sendMessage(chatId, "Вы уже отправили сегодняшний " +
                        "отчёт по выбранному питомцу. " +
                        "Обращаем Ваше внимание, что отчёт нужно заполнять один раз в день. " +
                        "Если произошла ошибка, свяжитесь, пожалуйста, с волонтёром - /volunteer. " +
                        "Или запросите обратный звонок - /callback");
                user.setUserStatus(UserStatus.JUST_USING);
                shelterUserRepository.save(user);
                return;
            }
            if (report.getPhotoId() != null && report.getEntry() == null) {
                MessageService.sendMessage(chatId, "Фото для данного " +
                        "отчёта уже было получено.\n" +
                        "Напишите, пожалуйста, письменный отчёт. " +
                        "В нём должно быть как можно более подробное описание:\n" +
                        "- рациона животного,\n" +
                        "- общего самочувствия и привыкания к новому месту,\n" +
                        "- изменений в поведении: отказ от старых привычек, приобретение " +
                        "новых.");
                user.setUserStatus(UserStatus.FILLING_REPORT);
                shelterUserRepository.save(user);
                return;
            }
        } else {
            report = new Report();
            report.setDate(LocalDate.now());
            report.setProbationPeriod(probPeriod);
        }
        Report savedReport = reportRepository.save(report);

        guardian.setCurrentReportId(savedReport.getId());
        guardianRepository.save(guardian);

        user.setUserStatus(UserStatus.FILLING_REPORT);
        shelterUserRepository.save(user);

        Animal animal = animalRepository.findById(animalId).orElseThrow();

        MessageService.sendMessage(chatId, "/sendReportSecondStep",
                "Вы выбрали " + animal.getNickname() +
                        ". Пришлите, пожалуйста, её/его фото.");
    }

    public void sendReportThirdStep(Long chatId, PhotoSize[] photo) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElseThrow();
        Guardian guardian = guardianRepository.findGuardianByUsername(user.getUsername()).orElseThrow();

        String photoId = Arrays.stream(photo)
                .sorted(Comparator.comparing(PhotoSize::fileSize).reversed())
                .findFirst()
                .orElse(null)
                .fileId();

        Report report = reportRepository.findById(guardian.getCurrentReportId()).orElseThrow();
        report.setPhotoId(photoId);
        Report savedReport = reportRepository.save(report);

        SendPhoto sendPhoto = new SendPhoto(chatId, savedReport.getPhotoId());
        MessageService.sendPhoto(sendPhoto);
        MessageService.sendMessage(chatId,
                "/sendReportThirdStep",
                "Спасибо! Фото получено.\n" +
                        "Теперь пришлите, пожалуйста, письменный отчёт.");
    }

    public void sendReportFourthStep(Long chatId, String messageText) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElseThrow();
        Guardian guardian = guardianRepository.findGuardianByUsername(user.getUsername()).orElseThrow();

        Report report = reportRepository.findById(guardian.getCurrentReportId()).orElseThrow();
        report.setEntry(messageText);
        Report savedReport = reportRepository.save(report);

        SendPhoto sendPhoto = new SendPhoto(chatId, savedReport.getPhotoId());
        SendMessage sendMessage = new SendMessage(chatId, savedReport.getEntry());

        MessageService.sendMessage(chatId,
                "/sendReportFourthStep",
                "Спасибо! Отчёт принят, и выглядит он вот так.");
        MessageService.sendPhoto(sendPhoto);
        MessageService.sendMessage(sendMessage);

        user.setUserStatus(UserStatus.JUST_USING);
        shelterUserRepository.save(user);

        guardian.setCurrentReportId(null);
        guardianRepository.save(guardian);
    }

    public void resetUsersShelterTypeAndCancel(Long chatId, ShelterUser user) {
        user.setUserStatus(UserStatus.JUST_USING);
        shelterUserRepository.save(user);
        MessageService.sendMessage(chatId, "/sendReportSecondStep",
                "Вы не выбрали животное. Чтобы попробовать ещё раз, " +
                        "пожалуйста, начните сначала, нажав на кнопку " +
                        "'Отправить отчёт' " +
                        "выше или сюда -> /start");
    }

    public static boolean isReportStatus(ShelterUser user) {
        UserStatus status = user.getUserStatus();
        if (status == UserStatus.FILLING_REPORT ||
                status == UserStatus.REPORT_YOU_WANNA_TRY_AGAIN ||
                status == UserStatus.CHOOSING_PET_FOR_REPORT) {
            return true;
        }
        return false;
    }

    public void reportHandler(Long chatId, String userMessage, PhotoSize[] photoSize) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElseThrow();

        if (user.getUserStatus() == UserStatus.REPORT_YOU_WANNA_TRY_AGAIN) {
            sendTryAgainMessage(chatId, userMessage, user);
            return;
        }
        if (user.getUserStatus() == UserStatus.CHOOSING_PET_FOR_REPORT) {
            resetUsersShelterTypeAndCancel(chatId, user);
            return;
        }

        Guardian guardian = guardianRepository.findGuardianByUsername(user.getUsername()).orElseThrow();
        Report report = this.reportRepository.findById(guardian.getCurrentReportId()).orElseThrow();

        if (photoSize != null && report.getPhotoId() == null) {
            sendReportThirdStep(chatId, photoSize);
            return;
        }
        if (userMessage != null && report.getPhotoId() != null) {
            if (userMessage.length() < 20) {
                MessageService.sendMessage(chatId, "incorrect length", "Информация указана не в полном объеме, попробуете еще раз?",
                        MenuService.createTwiceButtons(MenuService.YES, MenuService.NO));
                user.setUserStatus(UserStatus.REPORT_YOU_WANNA_TRY_AGAIN);
                shelterUserRepository.save(user);
                return;
            }
            sendReportFourthStep(chatId, userMessage);
            return;
        }
        if (userMessage != null && report.getPhotoId() == null) {
            MessageService.sendMessage(chatId, "Не правильный порядок действий", "Не правильный порядок действий. Нужно сперва загрузить фотографию. Попробуете еще раз?",
                    MenuService.createTwiceButtons(MenuService.YES, MenuService.NO));

            user.setUserStatus(UserStatus.REPORT_YOU_WANNA_TRY_AGAIN);
            shelterUserRepository.save(user);
        }
        if (photoSize != null && report.getPhotoId() != null) {
            MessageService.sendMessage(chatId, "Фото уже есть",
                    "Фото для данного отчёта уже было получено. Необходимо " +
                            "отправить письменный отчёт. Попробуете отправить письменный " +
                            "отчёт еще раз?",
                    MenuService.createTwiceButtons(MenuService.YES, MenuService.NO));

            user.setUserStatus(UserStatus.REPORT_YOU_WANNA_TRY_AGAIN);
            shelterUserRepository.save(user);
        }
    }

    public void sendTryAgainMessage(Long chatId, String userMessage, ShelterUser user) {
        if (userMessage.equalsIgnoreCase("ДА")) {
            user.setUserStatus(UserStatus.FILLING_REPORT);
            shelterUserRepository.save(user);
            MessageService.sendMessage(chatId, "Хорошо, тогда попробуйте еще раз");
        } else {
            user.setUserStatus(UserStatus.JUST_USING);
            shelterUserRepository.save(user);
            MessageService.sendMessage(chatId, "Нет? Тогда вернёмся к этому вопросу позже. Если нужно вернуться в начальное меню, используйте /start");
        }
    }
}
