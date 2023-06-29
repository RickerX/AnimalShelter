package com.example.animalshelter.repository;

import com.example.animalshelter.model.Shelter;
import com.example.animalshelter.model.ShelterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {
    Optional<Shelter> findByShelterType(ShelterType shelterType);
}
