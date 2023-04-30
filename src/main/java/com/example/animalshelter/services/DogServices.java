package com.example.animalshelter.services;

import com.example.animalshelter.model.Dog;

import java.util.Collection;

public interface DogServices {
    /**
     * Метод который выдает всех животных из БД.
     * @return Возвращает сиписок животных из БД.
     */
    Collection<Dog> getAll();

    /**
     * Метод который добавляет животных в БД.
     * @param dog идентификатор собаки, не может быть null.
     * @return Добавляет животное в БД.
     */
    Dog addDog(Dog dog);
}
