package com.example.animalshelter.repository;

import com.example.animalshelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter,Long> {
}
