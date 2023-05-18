package com.example.animalshelter.repository;

import com.example.animalshelter.model.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterUserRepository extends JpaRepository<ShelterUser,Long> {
}
