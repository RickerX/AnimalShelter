package com.example.animalshelter;

import com.example.animalshelter.listener.TelegramBotUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    TelegramBot telegramBot;

    @InjectMocks
    TelegramBotUpdatesListener listener;

    @Test
    void properMessageSendingTest() throws TelegramApiException {
        Long chatId = 123l;
        String mssg = "Hello";

        SendMessage sendMessage = new SendMessage(chatId, mssg);
        telegramBot.execute(sendMessage);
        Mockito.verify(telegramBot, atLeastOnce()).execute(sendMessage);
    }


}
