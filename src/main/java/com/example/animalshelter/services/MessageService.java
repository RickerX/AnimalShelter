package com.example.animalshelter.services;

import com.example.animalshelter.model.Shelter;
import com.example.animalshelter.repository.AnimalRepository;
import com.example.animalshelter.repository.InfoMessageRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final AnimalRepository animalRepository;
    private final InfoMessageRepository messageRepository;
    private static TelegramBot telegramBot;

    public MessageService(AnimalRepository animalRepository, InfoMessageRepository messageRepository) {
        this.animalRepository = animalRepository;
        this.messageRepository = messageRepository;
    }

    public static void setTelegramBot(TelegramBot telegramBot) {
        MessageService.telegramBot = telegramBot;
    }
    public static BaseResponse sendCallbackQueryResponse(String id) {
        return telegramBot.execute(new AnswerCallbackQuery(id));
    }

    public static void sendMessage(long chatId, String name, String text,
                                   InlineKeyboardMarkup keyboardMarkup) {
        logger.info("Sending the " + name + " message");

        SendMessage sendMessage = new SendMessage(chatId, text);
        SendResponse response;

        if (keyboardMarkup == null) {
            response = telegramBot.execute(sendMessage);
        } else {
            logger.info("Sending the keyboard");
            response = telegramBot.execute(sendMessage.replyMarkup(keyboardMarkup));
        }
        if (!response.isOk()) {
            logger.error("Could not send the " + name + " message! " +
                    "Error code: {}", response.errorCode());
        }
    }
    public static void sendMessage(long chatId, String nameOfMessageForLogger, String text) {
        sendMessage(chatId, nameOfMessageForLogger, text, null);
    }

    public static void sendMessage(long chatId, String text) {
        sendMessage(chatId, text, text, null);
    }

    public static void sendMessage(SendMessage sendMessage) {
        telegramBot.execute(sendMessage);
    }

    public static void sendPhoto(Long chatId, String caption, String imagePath) {
        try {
            File image = ResourceUtils.getFile("classpath:" + imagePath);
            SendPhoto sendPhoto = new SendPhoto(chatId, image);
            sendPhoto.caption(caption);
            telegramBot.execute(sendPhoto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void sendAddress(Long chatId, Shelter shelter) {
        telegramBot.execute(
                new SendMessage(chatId,
                        "Контактные данные приюта " + "'" + shelter.getTitle() + "'" + "\n" +
                                "Адрес: " + shelter.getAddress() + "\n" +
                                "Телефон: " + shelter.getPhoneNumber() + "\n" +
                                "Расписание: " + shelter.getSchedule()));
    }
    public static void sendPhoto(SendPhoto sendPhoto) {
        telegramBot.execute(sendPhoto);
    }

}
