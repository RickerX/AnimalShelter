package com.example.animalshelter.repository;

import com.example.animalshelter.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian,Long> {
}
