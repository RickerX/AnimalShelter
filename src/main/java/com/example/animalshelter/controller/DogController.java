package com.example.animalshelter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dog")
@Tag(name = "Собаки", description = "CRUD - операции")
public class DogController {
}
