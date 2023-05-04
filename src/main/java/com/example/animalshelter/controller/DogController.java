package com.example.animalshelter.controller;

import com.example.animalshelter.model.Dog;
import com.example.animalshelter.services.impl.DogServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Котроллер который отвечает за CRUD - операции.
 */
@RestController
@RequestMapping("/api/dog")
@Tag(name = "Собаки", description = "CRUD - операции")
public class DogController {
    private final DogServicesImpl dogServices;
    /**
     * Конструктор.
     * @param dogServices идентификатор.
     */
    public DogController(DogServicesImpl dogServices) {
        this.dogServices = dogServices;
    }
    /**
     * Метод который возвращает коллекцию собак.
     * @return возвращает всех собак из БД.
     */
    @GetMapping("/get")
    @Operation(summary = "Информация о всех собаках")
    public Collection<Dog> get() {
        return this.dogServices.getAll();
    }
    /**
     * Метод кторый добавляет собак в БД.
     * @param dog идентифиикатор, не может быть null.
     * @return добавляет собак в БД.
     */
    @PostMapping("/add")
    @Operation(summary = "Добавление собак в приют")
    public Dog add(Dog dog) {
        return this.dogServices.addDog(dog);
    }
}
