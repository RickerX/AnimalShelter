package com.example.animalshelter.services.impl;

import com.example.animalshelter.exception.InvalidAnimalRequestException;

import com.example.animalshelter.model.Dog;
import com.example.animalshelter.repository.DogRepository;
import com.example.animalshelter.services.DogServices;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class DogServicesImpl implements DogServices {
    private final DogRepository dogRepository;
    private final Map<Integer, Dog> dogMap = new HashMap<>();

    public DogServicesImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Метод создает объект и сохраняет в репозиторий.
     * <br>
     * @param dog идентификатор, не может быть null.
     * @return сохраняет в репозиторий.
     */
    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    /**
     * Все собаки в БД.
     * <br>
     * Используется метод репозитория {@link DogServices#getAll()}
     * @return Найдены все собаки в БД.
     */
    @Override
    public Collection<Dog> getAll() {
        return dogMap.values();
    }

    /**
     * Добавление собак в БД.
     * <br>
     * Используется метод репозитория {@link DogServices#addDog(Dog)}
     * @param dog идентификатор собаки, не может быть null.
     * @throws InvalidAnimalRequestException если собака под этим id уже существует.
     * @return Добавляет собаку в БД.
     */
    @Override
    public Dog addDog(Dog dog) {
        if (dogMap.containsKey(dog.getId())) {
            throw new InvalidAnimalRequestException("Собака под этим id уже есть");
        } else {
            dogMap.put(dog.getId(), dog);
        }
        return dog;
    }
}
