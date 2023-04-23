package com.example.animalshelter.controller;

import com.example.animalshelter.model.Cat;
import com.example.animalshelter.model.Dog;
import com.example.animalshelter.services.impl.CatServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/cat")
@Tag(name = "Коты", description = "CRUD - операции")
public class CatController {
    private final CatServicesImpl catServices;

    public CatController(CatServicesImpl catServices) {
        this.catServices = catServices;
    }

    @GetMapping("/get")
    @Operation(summary = "Информация о всех котах")
    public Collection<Cat> get() {
        return this.catServices.getAll();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление котов в приют")
    public Cat add(Cat cat) {
        return this.catServices.addCat(cat);
    }
}
