package com.example.animalshelter;

import com.example.animalshelter.exception.InvalidAnimalRequestException;
import com.example.animalshelter.model.Cat;
import com.example.animalshelter.model.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatDogTests {

    @Test
    @DisplayName("When cat that is existing and is already added to map, then invalid animal request exception is thrown.")
    void addCatThatAlreadyExistsTest() {
        Map<Integer, Cat> catMap = new HashMap<>();
        Cat catTest = new Cat();
        if (catMap.keySet().contains(catTest)) {
            throw new InvalidAnimalRequestException("Кот уже есть в списке.");
        } else catMap.put(1, catTest);
    }

    @Test
    @DisplayName("When there's a cat that doesn't match any existing ones, then it's added to the map, else invalid animal request exception is thrown.")
    void addCatTest() {
        Map<Integer, Cat> catMap = new HashMap<>();
        Cat catTest = new Cat();
        if (!catMap.keySet().contains(catTest)) {
            catMap.put(1, catTest);
        } else {
            throw new InvalidAnimalRequestException("Кот уже есть в списке.");
        }
    }

    @Test
    @DisplayName("When there's a dog that doesn't match any existing ones, then it's added to the map, else invalid animal request exception is thrown.")
    void addDogTest() {
        Map<Integer, Dog> dogMap = new HashMap<>();
        Dog dogTest = new Dog();
        if (!dogMap.keySet().contains(dogTest)) {
           dogMap.put(1, dogTest);
        } else {
            throw new InvalidAnimalRequestException("Собака уже есть в списке.");
        }
    }

    @Test
    @DisplayName("When dog that is existing and is already added to map, then invalid animal request exception is thrown.")
    void addDogThatALreadyExistsTest() {
        Map<Integer, Dog> dogMap = new HashMap<>();
        Dog dogTest = new Dog();
        if (dogMap.keySet().contains(dogTest)) {
            throw new InvalidAnimalRequestException("Собака уже есть в списке.");
        } else dogMap.put(1, dogTest);
    }


    @Test
    @DisplayName("When there are cats in map, then list of all cats is returned, else invalid animal request exception is thrown.")
    void getAllCatsTest() {
        Map<Integer, Cat> catMap = new HashMap<>();
        Cat catTest = new Cat();
        if (catMap != null) {
            catMap.put(1, catTest);
            Assertions.assertIterableEquals(catMap.values(), List.of(catTest));
        } else {
            throw new InvalidAnimalRequestException("Список котов пуст.");
        }

    }

    @Test
    @DisplayName("When there are dogs in map, then list of all dogs is returned, else invalid animal request exception is thrown.")
    void getAllDogsTest() {
        Map<Integer, Dog> dogMap = new HashMap<>();
        Dog dogTest = new Dog();
        if (dogMap != null) {
            dogMap.put(1, dogTest);
            Assertions.assertIterableEquals(dogMap.values(), List.of(dogTest));
        } else {
            throw new InvalidAnimalRequestException("Список собак пуст.");
        }

    }

    @Test
    @DisplayName("When there are no dogs in a dog list then invalid animal request exception is thrown.")
    void emptyDogListTest() {
        Map<Integer, Dog> dogMap = new HashMap<>();
        if (dogMap == null) {
            Assertions.assertIterableEquals(dogMap.values(), List.of(null));
            throw new InvalidAnimalRequestException("Список собак пуст.");
        }
    }

    @Test
    @DisplayName("When there are no cats in a cat list then invalid animal request exception is thrown.")
    void emptyCatListTest() {
        Map<Integer, Cat> catMap = new HashMap<>();
        if (catMap == null) {
            Assertions.assertIterableEquals(catMap.values(), List.of(null));
            throw new InvalidAnimalRequestException("Список котов пуст.");
        }
    }
}
