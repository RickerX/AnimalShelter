package com.example.animalshelter.controller;

import com.example.animalshelter.model.Dog;
import com.example.animalshelter.services.impl.DogServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/dog")
@Tag(name = "Собаки", description = "CRUD - операции")
public class DogController {
    private final DogServicesImpl dogServices;

    public DogController(DogServicesImpl dogServices) {
        this.dogServices = dogServices;
    }

    @GetMapping("/get")
    @Operation(summary = "Информация о всех собаках")
    public Collection<Dog> get() {
        return this.dogServices.getAll();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление собак в приют")
    public Dog add(Dog dog) {
        return this.dogServices.addDog(dog);
    }
}
