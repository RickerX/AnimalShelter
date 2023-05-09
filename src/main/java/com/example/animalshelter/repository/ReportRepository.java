package com.example.animalshelter.repository;

import com.example.animalshelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
