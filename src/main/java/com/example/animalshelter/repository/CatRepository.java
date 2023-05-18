package com.example.animalshelter.repository;

import com.example.animalshelter.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}
