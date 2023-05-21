package com.example.animalshelter.listener;

import com.example.animalshelter.services.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.example.animalshelter.services.UserService;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final UserService userService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, UserService userService) {
        this.telegramBot = telegramBot;
        this.userService = userService;
    }
//    @PostConstruct
//    public void init() {
//        telegramBot.setUpdatesListener(this);
//
//    }

//    @Override
//    public int process(List<Update> updates) {
//        try {
//            updates.forEach(update -> {
//                logger.info("Handles update: {}", update);
//                Message message = update.message();
//                Long chatId = message.chat().id();
//                String text = message.text();
//                if ("/start".equals(text)) {
//                    SendMessage sendMessage = new SendMessage(chatId, "Hello!");
//                    SendResponse sendResponse = telegramBot.execute(sendMessage);
//                    if (!sendResponse.isOk()) {
//                        logger.error("Error during sending message: {}", sendResponse.description());
//                    }
//                }
//
//            });
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//        }
//
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
        MessageService.setTelegramBot(this.telegramBot);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            userService.updateHandler(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
