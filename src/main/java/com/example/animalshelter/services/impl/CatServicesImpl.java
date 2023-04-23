package com.example.animalshelter.services.impl;

import com.example.animalshelter.exception.InvalidAnimalRequestException;
import com.example.animalshelter.model.Cat;
import com.example.animalshelter.services.CatServices;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CatServicesImpl implements CatServices {
    private final Map<Integer, Cat> catMap = new HashMap<>();
    @Override
    public Collection<Cat> getAll() {
        return catMap.values();
    }
    @Override
    public Cat addDog(Cat cat) {
        if (catMap.containsKey(cat.getId())) {
            throw new InvalidAnimalRequestException("Собака под этим id уже есть");
        } else {
            catMap.put(cat.getId(), cat);
        }
        return cat;
    }
}
