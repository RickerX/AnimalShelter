package com.example.animalshelter.services.impl;

import com.example.animalshelter.exception.InvalidAnimalRequestException;
import com.example.animalshelter.model.Cat;

import com.example.animalshelter.repository.CatRepository;
import com.example.animalshelter.services.CatServices;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CatServicesImpl implements CatServices {
    private final CatRepository catRepository;
    private final Map<Integer, Cat> catMap = new HashMap<>();

    public CatServicesImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    /**
     * Все коты в БД.
     * <br>
     * Используется метод репозитория {@link CatServices#getAll()}
     * @return Найдены все коты в БД.
     */
    @Override
    public Collection<Cat> getAll() {
        return catMap.values();
    }
    /**
     * Добавление собак в БД.
     * <br>
     * Используется метод репозитория {@link CatServices#addCat(Cat)}
     * @param cat идентификатор кота, не может быть null.
     * @throws InvalidAnimalRequestException если кот под этим id уже существует.
     * @return Добавляет кота в БД.
     */
    @Override
    public Cat addCat(Cat cat) {
        if (catMap.containsKey(cat.getId())) {
            throw new InvalidAnimalRequestException("Кошка под этим id уже есть");
        } else {
            catMap.put(cat.getId(), cat);
        }
        return cat;
    }
}
