package com.example.animalshelter.services;
import com.example.animalshelter.model.ShelterType;
import com.example.animalshelter.model.ShelterUser;
import com.example.animalshelter.model.UserStatus;
import com.example.animalshelter.repository.ShelterUserRepository;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final ShelterUserRepository shelterUserRepository;
    private final InfoMessageService infoMessageService;
    private final CallbackService callbackService;
    private final ReportService reportService;
    private final AdminService adminService;

    public UserService(ShelterUserRepository shelterUserRepository, InfoMessageService infoMessageService, CallbackService callbackService, ReportService reportService, AdminService adminService) {
        this.shelterUserRepository = shelterUserRepository;
        this.infoMessageService = infoMessageService;
        this.callbackService = callbackService;
        this.reportService = reportService;
        this.adminService = adminService;
    }

    public void updateHandler(Update update) {
        if (update.message() != null) {

            Long chatId = update.message().chat().id();
            String userMessage = update.message().text();
            String username = update.message().chat().username();
            PhotoSize[] photoSize = update.message().photo();

            if (this.shelterUserRepository.findById(chatId).isEmpty()) {
                sendFirstGreetings(chatId, username);
                this.shelterUserRepository.save(new ShelterUser(chatId, UserStatus.JUST_USING, ShelterType.DOG_SHELTER,"test","test"));
                messageHandler(chatId, "/start");
                return;
            }
            ShelterUser user = shelterUserRepository.findById(chatId).orElse(null);
            if (user == null) {
                return;
            }

            if (userMessage != null && user.getUserStatus() == UserStatus.JUST_USING) {
                messageHandler(chatId, userMessage);
                return;
            }
            if (userMessage != null && user.getUserStatus() == UserStatus.SENDING_ADOPTER_USERNAME) {
                adminService.addUsernameToGuardianDatabase(chatId, userMessage);
                return;
            }
            if (ReportService.isReportStatus(user)) {
                reportService.reportHandler(chatId, userMessage, photoSize);
                return;
            }
            if (userMessage != null && callbackService.isCallbackRequest(userMessage, chatId)) {
                callbackService.sendCallbackMessage(userMessage, chatId);
                return;
            }
        }
        if (update.callbackQuery() != null) {

            MessageService.sendCallbackQueryResponse(update.callbackQuery().id());
            Long chatId2 = update.callbackQuery().message().chat().id();
            String text = update.callbackQuery().data();
            ShelterUser user = shelterUserRepository.findById(chatId2).orElseThrow();
            if (user.getUserStatus() == UserStatus.CHOOSING_PET_FOR_REPORT) {
                reportService.sendReportSecondStep(chatId2, text);
            } else {
                messageHandler(chatId2, MenuService.getCommandByButton(text));
            }
        }
    }

    private void messageHandler(long chatId, String userMessage) {
        ShelterUser user = shelterUserRepository.findById(chatId).orElse(null);
        if (user == null) {
            return;
        }
        if (ReportService.isReportStatus(user)) {
            reportService.reportHandler(chatId, userMessage, null);
            return;
        }
        if (userMessage.equals("/admin")) {
            adminService.sendAdminMenu(chatId);
            return;
        }
        if (userMessage.equals("add adopter username")) {
            adminService.sendGuardianUsernameInputMessage(chatId);
            return;
        }
        if (userMessage.startsWith("/menu")) {
            switch (userMessage) {
                case "/menuChoiceShelter" -> MenuService.sendChoiceShelterMenu(chatId);
                case "/menuMainShelter" -> MenuService.sendMainShelterMenu(chatId);
                case "/menuAboutShelter" -> MenuService.sendAboutShelterMenu(chatId);
                case "/menuAnimalGuide" -> MenuService.sendAnimalGuideMenu(chatId);
                default -> MessageService.sendMessage(chatId, "incorrect menu request!");
            }
        } else if (userMessage.equals("/start")){
            MessageService.sendPhoto(chatId, "", "images/animal-shelter-logo.jpg");
            MenuService.sendChoiceShelterMenu(chatId);
        } else if (userMessage.equals("/catShelter")) {
            user.setShelterType(ShelterType.CAT_SHELTER);
            MessageService.sendMessage(chatId, "Вы выбрали приют для котов");
            MessageService.sendPhoto(chatId, "", "images/cat-shelter.jpg");
            MenuService.sendMainShelterMenu(chatId);
            this.shelterUserRepository.save(user);
        } else if (userMessage.equals("/dogShelter")) {
            user.setShelterType(ShelterType.DOG_SHELTER);
            MessageService.sendMessage(chatId, "Вы выбрали приют для собак");
            MessageService.sendPhoto(chatId, "", "images/dog-shelter.jpg");
            MenuService.sendMainShelterMenu(chatId);
            this.shelterUserRepository.save(user);
        } else if (infoMessageService.isInfo(userMessage, chatId)) {
            infoMessageService.sendInfoMessage(chatId, userMessage);
        } else if (userMessage.equals("/addressAndSchedule")) {
            MessageService.sendMessage(chatId,"Адрес и часы работы приюта для животных:\n" +
                    "г.Астана ул.Гагарина д.1\n" +
                    "пн-пт 08:00-20:00\n" +
                    "сб-вс 08:00-18:00");
        } else if (userMessage.equals("/datingRules")) {
            MessageService.sendMessage(chatId,"Правила знакомства с животным:\n" +
                    "1.необходимо быть спокойным и доброжелательным, чтобы животное могло доверять\n" +
                    "2.необходимо набраться терпения\n" +
                    "3.необходимо быть заботливым и любящим");
        } else if (userMessage.equals("/homeAnimal")) {
            MessageService.sendMessage(chatId,"Список рекомендаций по обустройству дома для животного:\n" +
                    "1.необходимо обеспечить наличие корма, воды и туалета\n" +
                    "2.необходимо создать для животного отдельный и уютный уголок\n" +
                    "3.необходимо создать максимально доверительные отношения с животным");
        } else if (userMessage.equals("/docList")) {
            MessageService.sendMessage(chatId,"Необходимые документы:\n" +
                    "Паспорт и ксерокопия\n" +
                    "фото 3*4");
        } else if (userMessage.equals("/reasonsRefusal")) {
            MessageService.sendMessage(chatId,"Список причин для отказа взять животное из приюта:\n" +
                    "1.Человек находится в состоянии алкогольного опьянения\n" +
                    "2.Человек с психическими и умственными отклонениями\n" +
                    "3.Человек, не достигший совершеннолетнего возраста\n" +
                    "4.Человек, не зарекомендовавший себя как благонадежный");
        } else if (callbackService.isCallbackRequest(userMessage, chatId)) {
            callbackService.sendCallbackMessage(userMessage, chatId);
        } else if (userMessage.equals("/volunteer")) {
            MessageService.sendMessage(chatId, "Ок, позову свободного Волонтера");
        } else if (reportService.isSendReportCommand(userMessage)) {
            reportService.sendReportFirstStep(chatId);
        } else {
            MessageService.sendMessage(chatId, "default", "Не понимаю... попробуй /start");
        }
    }

    private void sendFirstGreetings(Long chatId, String userName) {
        MessageService.sendMessage(chatId, "first greeting", "Привет! Я бот приюта для животных.\n" +
                "Могу рассказать о приюте , а так же о том, что необходимо сделать, чтобы забрать питомца домой.");
    }
}
