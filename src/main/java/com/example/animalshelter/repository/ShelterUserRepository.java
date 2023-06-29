package com.example.animalshelter.repository;

import com.example.animalshelter.model.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser,Long> {
    Optional<ShelterUser> findByUsername(String username);
}
