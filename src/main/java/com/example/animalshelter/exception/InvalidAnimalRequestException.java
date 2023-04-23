package com.example.animalshelter.exception;

public class InvalidAnimalRequestException extends RuntimeException{
    public InvalidAnimalRequestException(String message) {
        super(message);
    }
}
