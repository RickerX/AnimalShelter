package com.example.animalshelter.repository;

import com.example.animalshelter.model.ProbationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProbationPeriodRepository extends JpaRepository<ProbationPeriod,Long> {
    ProbationPeriod findByAnimal_Id(Long animalId);
}
