package com.example.animalshelter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    /**
     * Запуск приложения.
     * @return Приложение запущено.
     */
    @GetMapping("/")
    @Tag(name = "Запуск приложения")
    public String startApp() {
        return "Приложение запущено";
    }

    /**
     * Информация о приложении.
     * @return Имя: Роман,Оксана,Алиса,Название проекта: 'Animal Shelter',Дата создания проекта: '17.04.2023',Описание проекта: 'Название: Animal Shelter,Функции'Приют для животных',Java 17'
     */
    @GetMapping("/info")
    @Tag(name = "Информация",description = "Информация о приложении")
    public String info() {
        return "Имя: Роман,Оксана,Алиса,Название проекта: 'Animal Shelter',Дата создания проекта: '17.04.2023',Описание проекта: 'Название: Animal Shelter,Функции'Приют для животных',Java 17'";
    }
}
