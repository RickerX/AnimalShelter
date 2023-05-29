package com.example.animalshelter.services;

import com.example.animalshelter.model.*;
import com.example.animalshelter.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AdminService {
    private final GuardianRepository guardianRepository;
    private final ShelterUserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;
    private final ProbationPeriodRepository probationPeriodRepository;

    public AdminService(GuardianRepository guardianRepository, ShelterUserRepository userRepository, AnimalRepository animalRepository, ShelterRepository shelterRepository, ProbationPeriodRepository probationPeriodRepository) {
        this.guardianRepository = guardianRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
        this.shelterRepository = shelterRepository;
        this.probationPeriodRepository = probationPeriodRepository;
    }
//    public void sendRoadMapUpdateInstruction(Long chatId) {
//        MessageService.sendMessage(chatId, "map road update instruction","Выберите, для какого приюта загрузить карту проезда.\n" +
//                "\n" +
//                "Отправьте соответствующую картинку со схемой проезда и подпишите отправляемое сообщение с фотографией следующим образом:\n" +
//                "\n" +
//                "если схема для приюта кошек, подпишите сообщение:\n" +
//                "shelter-map-1\n"+
//                "если схема для приюта собак, подпишите сообщение:\n" +
//                "shelter-map-2");
//    }

    public void sendAdminMenu(Long chatId) {
        MessageService.sendMessage(chatId, "admin menu", "Привет, админ, выбери меню:", MenuService.createTwiceButtons("update road map", "add guardian username"));
    }

    public void sendGuardianUsernameInputMessage(Long chatId) {
        MessageService.sendMessage(chatId, "add Username To Admin Database", "Введи username, добавлю нового опекуна");
        ShelterUser user = userRepository.findById(chatId).orElseThrow();
        user.setUserStatus(UserStatus.SENDING_ADOPTER_USERNAME);
        userRepository.save(user);
    }

    public void addUsernameToGuardianDatabase(Long chatId, String username) {
        ShelterUser user = userRepository.findById(chatId).orElseThrow();
        user.setUserStatus(UserStatus.JUST_USING);
        userRepository.save(user);
        Guardian guardian = new Guardian();
        guardian.setUsername(username);
        guardian.setAddress("test address");
        guardian.setName(username);
        guardian.setAge(25);
        guardian.setChatId(chatId);
        guardian.setPhoneNumber("test number");
        guardianRepository.save(guardian);
        MessageService.sendMessage(chatId, "guardian добавлен в базу данных усыновителей");
        addAnimalAndProbationPeriod(chatId, guardian);
        MessageService.sendMessage(chatId, "animal добавлен в базу данных животных");
        MessageService.sendMessage(chatId, "probation period добавлен в базу данных");
        MenuService.sendChoiceShelterMenu(chatId);
    }

    private void addAnimalAndProbationPeriod(Long chatId, Guardian guardian) {
        Animal animal = new Animal();
        int random = (int) (Math.random() * 15);
        animal.setAge(random);
        animal.setNickname("nickname" + random);
        animal.setGuardian(guardian);
        animal.setClient(guardian);
        animal.setColor("color" + random);
        Shelter shelter = shelterRepository.findById(1L).orElseThrow();
        animal.setShelter(shelter);
        this.animalRepository.save(animal);
        ProbationPeriod probationPeriod = new ProbationPeriod();
        probationPeriod.setGuardian(guardian);
        probationPeriod.setEnds(LocalDate.now().plusDays(30L));
        probationPeriod.setAnimal(animal);
        probationPeriod.setVolunteersComment("testComment" + random);
        this.probationPeriodRepository.save(probationPeriod);
    }
}
