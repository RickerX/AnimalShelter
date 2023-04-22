package com.example.animalshelter.services;

import com.example.animalshelter.model.Cat;

import java.util.Collection;

public interface CatServices {
    Collection<Cat> getAll();

    Cat addDog(Cat cat);
}
