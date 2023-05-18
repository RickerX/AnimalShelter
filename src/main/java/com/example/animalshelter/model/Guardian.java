package com.example.animalshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long chatId;
    private String username;
    private String name;
    private int age;
    private String address;

    private String phoneNumber;

    public Guardian() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return id == guardian.id && chatId == guardian.chatId && age == guardian.age && Objects.equals(username, guardian.username) && Objects.equals(name, guardian.name) && Objects.equals(address, guardian.address) && Objects.equals(phoneNumber, guardian.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, username, name, age, address, phoneNumber);
    }
}
