package com.example.animalshelter.services;

import com.example.animalshelter.model.Dog;

import java.util.Collection;

public interface DogServices {

    Collection<Dog> getAll();

    Dog addDog(Dog dog);
}
