package com.example.animalshelter.services;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    public static final String CAT_SHELTER = "Приют для кошек";
    public static final String DOG_SHELTER = "Приют для собак";
    public static final String SHELTER_INFO = "Информация о приюте";
    public static final String ANIMAL_INFO = "Как взять питомца из приюта";
    public static final String SEND_REPORT = "Отправить отчёт";
    public static final String CALL_VOLUNTEER = "Позвать волонтёра";
    public static final String ADDRESS_SCHEDULE = "Адрес и часы работы";
    public static final String CALLBACK = "Обратный звонок";
    public static final String YES = "Да";
    public static final String NO = "Нет";


    public static void sendChoiceShelterMenu(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = createTwiceButtons(CAT_SHELTER, DOG_SHELTER);
        MessageService.sendMessage(chatId, "choice shelter menu", "Выбери раздел:", keyboardMarkup);
    }

    public static void sendAnimalGuideMenu(long chatId) {
        MessageService.sendMessage(chatId, "animal guide menu", "Вот что я могу тебе рассказать:\n" +
                "\n" +
                "/datingRules - правила знакомства с животным.\n" +
                "/docList - список документов, необходимых для того, чтобы взять животное из приюта.\n" +
                "/homeAnimal - список рекомендаций по обустройству дома для животного.\n" +
                "/reasonsRefusal - список причин для отказа взять животное из приюта.\n" +
                "\n" +
                "Не нашли ответа на свой вопрос?\n" +
                "\n" +
                "/callback - запросить обратный звонок\n" +
                "/volunteer - позвать волонтёра в чат");
    }
    public static void sendMainShelterMenu(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = createTwiceButtons(SHELTER_INFO, ANIMAL_INFO, SEND_REPORT, CALL_VOLUNTEER);
        MessageService.sendMessage(chatId, "main shelter menu", "Выбери раздел:", keyboardMarkup);
    }
    public static void sendAboutShelterMenu(Long chatId) {
        InlineKeyboardMarkup keyboardMarkup = createTwiceButtons( ADDRESS_SCHEDULE, CALLBACK, CALL_VOLUNTEER);
        MessageService.sendMessage(chatId, "about shelter menu", "Выбери раздел:", keyboardMarkup);
    }

    public static InlineKeyboardMarkup createTwiceButtons(String ... buttons) {
        int length = buttons.length;
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        for (int i = 0; i < length / 2; i++) {
            InlineKeyboardButton button1 = new InlineKeyboardButton(buttons[i*2]);
            InlineKeyboardButton button2 = new InlineKeyboardButton(buttons[i*2+1]);
            button1.callbackData(button1.text());
            button2.callbackData(button2.text());
            keyboardMarkup.addRow(button1, button2);
        }
        if (length % 2 != 0) {
            InlineKeyboardButton button1 = new InlineKeyboardButton(buttons[length - 1]);
            button1.callbackData(button1.text());
            keyboardMarkup.addRow(button1);
        }
        return keyboardMarkup;
    }
    public static String getCommandByButton(String text) {
        String command;
        switch (text) {
            case CAT_SHELTER -> command = "/catShelter";
            case DOG_SHELTER -> command = "/dogShelter";

            case YES -> command = "ДА";
            case NO -> command = "НЕТ";

            case SHELTER_INFO -> command = "/menuAboutShelter";
            case ANIMAL_INFO -> command = "/menuAnimalGuide";
            case SEND_REPORT -> command = "/sendReport";
            case CALL_VOLUNTEER -> command = "/volunteer";
            case CALLBACK -> command = "/callback";
            case ADDRESS_SCHEDULE -> command = "/addressAndSchedule";
            default -> command = text;
        }
        return command;
    }

}
