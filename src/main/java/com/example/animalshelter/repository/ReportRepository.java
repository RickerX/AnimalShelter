package com.example.animalshelter.repository;

import com.example.animalshelter.model.ProbationPeriod;
import com.example.animalshelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    boolean existsByProbationPeriodAndDate(ProbationPeriod probationPeriod,
                                           LocalDate date);

    Optional<Report> findReportByProbationPeriodAndDate(ProbationPeriod probationPeriod,
                                                        LocalDate date);
}
