package com.example.animalshelter.services;

import com.example.animalshelter.model.ShelterType;
import com.example.animalshelter.repository.CatMessageRepository;
import com.example.animalshelter.repository.DogMessageRepository;
import com.example.animalshelter.repository.ShelterRepository;
import com.example.animalshelter.repository.ShelterUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InfoMessageService {
    private final CatMessageRepository catMessageRepository;
    private final DogMessageRepository dogMessageRepository;
    private final ShelterUserRepository shelterUserRepository;
    private final ShelterRepository shelterRepository;

    public InfoMessageService(CatMessageRepository catMessageRepository,
                              DogMessageRepository dogMessageRepository, ShelterUserRepository shelterUserRepository,
                              ShelterRepository shelterRepository) {
        this.catMessageRepository = catMessageRepository;
        this.dogMessageRepository = dogMessageRepository;
        this.shelterUserRepository = shelterUserRepository;
        this.shelterRepository = shelterRepository;
    }
}
