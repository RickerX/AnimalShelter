package com.example.animalshelter.services;

import com.example.animalshelter.model.Cat;

import java.util.Collection;

public interface CatServices {
    /**
     * Метод который выдает всех животных из БД.
     * @return Возвращает сиписок животных из БД.
     */
    Collection<Cat> getAll();

    /**
     * Метод который добавляет животных в БД.
     * @param cat идентификатор кота, не может быть null.
     * @return Добавляет животное в БД.
     */
    Cat addCat(Cat cat);
}

