package com.example.animalshelter.services;

import com.example.animalshelter.repository.*;

public class ReportService {
    private final ShelterUserRepository shelterUserRepository;
    private final GuardianRepository adopterRepository;
    private final ReportRepository reportRepository;
    private final ProbationPeriodRepository probationPeriodRepository;
    private final AnimalRepository animalRepository;

    public ReportService(ShelterUserRepository shelterUserRepository, GuardianRepository adopterRepository, ReportRepository reportRepository, ProbationPeriodRepository probationPeriodRepository, AnimalRepository animalRepository) {
        this.shelterUserRepository = shelterUserRepository;
        this.adopterRepository = adopterRepository;
        this.reportRepository = reportRepository;
        this.probationPeriodRepository = probationPeriodRepository;
        this.animalRepository = animalRepository;
    }
}
