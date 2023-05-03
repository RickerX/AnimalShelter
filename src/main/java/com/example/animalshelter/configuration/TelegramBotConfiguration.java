package com.example.animalshelter.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfiguration {
    private final String token;

    public TelegramBotConfiguration(@Value("${telegram.bot.token}") String token) {
        this.token = token;
    }
    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

//    @Bean
//    public TelegramBot telegramBot() {
//        return new TelegramBot(token);
//    }
//    @Bean
//    public TelegramBot telegramBot() {
//        TelegramBot telegramBot = new TelegramBot(token);
//        telegramBot.execute(new DeleteMyCommands());
//        return telegramBot;
//    }
}
