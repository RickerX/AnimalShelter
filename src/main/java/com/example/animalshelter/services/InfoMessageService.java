package com.example.animalshelter.services;

import com.example.animalshelter.model.InfoMessage;
import com.example.animalshelter.model.Shelter;
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
    public void sendInfoMessage(Long chatId, String tag) {
        InfoMessage infoMessage = (InfoMessage) getRepository(chatId).findById(tag).orElseThrow(RuntimeException::new);
        MessageService.sendMessage(chatId, tag, infoMessage.getText());
        if (tag.equals("/addressandschedule")) {
            ShelterType shelterType = shelterUserRepository.findById(chatId).orElseThrow().getShelterType();
            Shelter shelter = shelterRepository.findByShelterType(shelterType).get();
            MessageService.sendAddress(chatId, shelter);
        }
    }

    public JpaRepository<? extends Object, String> getRepository(Long chatId) {
        ShelterType shelterType;
        shelterType = shelterUserRepository.findById(chatId).orElseThrow().getShelterType();
        if (shelterType == null) {
            throw new RuntimeException();
        }
        if (shelterType.equals(ShelterType.CAT_SHELTER)) {
            return catMessageRepository;
        } else if (shelterType.equals(ShelterType.DOG_SHELTER)) {
            return dogMessageRepository;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean isInfo(String userMessage, Long chatId) {
        return getRepository(chatId).findById(userMessage).isPresent();
    }


}
