package com.example.animalshelter.services;
import com.example.animalshelter.model.ShelterType;
import com.example.animalshelter.model.ShelterUser;
import com.example.animalshelter.model.UserStatus;
import com.example.animalshelter.repository.ShelterUserRepository;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ShelterUserRepository userRepository;
    private final MessageService messageService;

    public UserService(ShelterUserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    public void updateHandler(Update update) {
        if (update.message() != null) {

            Long chatId = update.message().chat().id();
            String userMessage = update.message().text();
            String username = update.message().chat().username();


            if (!this.userRepository.findById(chatId).isPresent()) {
                sendFirstGreetings(chatId, username);
//                this.userRepository.save(new ShelterUser(chatId, ShelterType.DOG_SHELTER, null, username));
                messageHandler(chatId, "/start");
                return;
            }

            ShelterUser user = userRepository.findById(chatId).orElse(null);
            if (user == null) {
                return;
            }

            if (userMessage != null && user.getUserStatus() == UserStatus.JUST_USING) {
                messageHandler(chatId, userMessage);
            }
        }
    }

    private void messageHandler(long chatId, String userMessage) {
        ShelterUser user = userRepository.findById(chatId).orElse(null);
        if (userMessage.startsWith("/menu")) {
            switch (userMessage) {
                case "/menuChoiceShelter" -> MenuService.sendChoiceShelterMenu(chatId);
                case "/menuAnimalGuide" -> MenuService.sendAnimalGuideMenu(chatId);
                default -> MessageService.sendMessage(chatId, "incorrect menu request!");
            }
//            if (user == null) {
//                return;
//            }
        } else if (userMessage.equals("/start")){
            MenuService.sendChoiceShelterMenu(chatId);
        } else if (userMessage.equals("/catShelter")) {
            user.setShelterType(ShelterType.CAT_SHELTER);
            MessageService.sendMessage(chatId, "Вы выбрали приют для котов");
            this.userRepository.save(user);
        } else if (userMessage.equals("/dogShelter")) {
            MessageService.sendMessage(chatId, "Вы выбрали приют для собак");
            user.setShelterType(ShelterType.DOG_SHELTER);
            this.userRepository.save(user);
        } else {
            MessageService.sendMessage(chatId, "default", "попробуй /start");
        }
    }
    private void sendFirstGreetings(Long chatId, String userName) {
        MessageService.sendMessage(chatId, "first greeting", "Привет! Я бот приюта для животных.\n" +
                "Могу рассказать о приюте , а так же о том, что необходимо сделать, чтобы забрать питомца домой.");
    }
}
