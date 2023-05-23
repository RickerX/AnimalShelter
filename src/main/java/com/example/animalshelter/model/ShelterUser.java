package com.example.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
@Entity
@Table(name = "shelter_user")
public class ShelterUser {
    @Id
    private long chatId;
    ShelterType shelterType;
    UserStatus userStatus;
    String phoneNumber;
    String username;

    public ShelterUser() {
    }

    public ShelterUser(long chatId, UserStatus userStatus, ShelterType shelterType, String phoneNumber, String username) {
        this.chatId = chatId;
        this.userStatus = userStatus;
        this.shelterType = shelterType;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public ShelterType getShelterType() {
        return shelterType;
    }

    public void setShelterType(ShelterType shelterType) {
        this.shelterType = shelterType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterUser that = (ShelterUser) o;
        return chatId == that.chatId && shelterType == that.shelterType && userStatus == that.userStatus && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, shelterType, userStatus, phoneNumber, username);
    }
}
