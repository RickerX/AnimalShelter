package com.example.animalshelter.repository;

import com.example.animalshelter.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {
}
