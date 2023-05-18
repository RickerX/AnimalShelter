package com.example.animalshelter.repository;

import com.example.animalshelter.model.DogMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogMessageRepository extends JpaRepository<DogMessage,String> {
}
