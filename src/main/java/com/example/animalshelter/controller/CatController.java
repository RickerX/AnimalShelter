package com.example.animalshelter.controller;
import com.example.animalshelter.model.Cat;
import com.example.animalshelter.services.impl.CatServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Котроллер который отвечает за CRUD - операции.
 */
@RestController
@RequestMapping("/api/cat")
@Tag(name = "Коты", description = "CRUD - операции")
public class CatController {
    private final CatServicesImpl catServices;

    /**
     * Конструктор.
     * @param catServices идентификатор.
     */
    public CatController(CatServicesImpl catServices) {
        this.catServices = catServices;
    }

    /**
     * Метод который возвращает коллекцию котов.
     * @return возвращает всех котов из БД.
     */
    @GetMapping("/get")
    @Operation(summary = "Информация о всех котах")
    public Collection<Cat> get() {
        return this.catServices.getAll();
    }

    /**
     * Метод кторый добавляет котов в БД.
     * @param cat идентифиикатор, не может быть null.
     * @return добавляет котов в БД.
     */
    @PostMapping("/add")
    @Operation(summary = "Добавление котов в приют")
    public Cat add(Cat cat) {
        return this.catServices.addCat(cat);
    }
}
