package com.example.animalshelter.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfiguration {
    /**
     * Тип String, название переменной token
     */
    private final String token;

    /**
     * Токен бота.
     * @param token Идентификатор бота.
     */
    public TelegramBotConfiguration(@Value("${telegram.bot.token}") String token) {
        this.token = token;
    }

    /**
     * Метод который возвращает токен.
     * @return Возврат токена.
     */
    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(token);
    }
}
