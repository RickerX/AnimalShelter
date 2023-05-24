package com.example.animalshelter;
import com.example.animalshelter.listener.TelegramBotUpdatesListener;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AnimalShelterApplicationTests {

    @MockBean
    TelegramBot telegramBot;

    @Autowired
    TelegramBotUpdatesListener updatesListener;

    @AfterEach
    public void clean(){

    }
    @Test
    void contextLoads() {
    }

    @Test
    void testMenu() {
        when(telegramBot.execute(any())).then((invocation) -> {
            Object arg = invocation.getArgument(0);
            SendMessage mssg = (SendMessage) arg;
            assertThat(mssg.getParameters().get("text")).isEqualTo("Hello!");
            return null;
        });
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/start");
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(1L);
        updatesListener.process(List.of(update));
    }

}
