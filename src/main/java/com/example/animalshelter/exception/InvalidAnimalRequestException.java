package com.example.animalshelter.exception;

public class InvalidAnimalRequestException extends RuntimeException{
    /**
     * Собстенный Exeption.
     * @param message Возвращает причину ошибки.
     */
    public InvalidAnimalRequestException(String message) {
        super(message);
    }
}
