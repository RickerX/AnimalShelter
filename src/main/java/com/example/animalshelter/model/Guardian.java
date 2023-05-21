package com.example.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guardian_id")
    private Long id;
    private Long chatId;
    private String username;
    private String name;
    private int age;
    private String address;

    private String phoneNumber;
    @OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY)
//    @JoinColumn
    @JsonBackReference
    private List<Animal> animals;
    @OneToMany(mappedBy = "guardian", fetch = FetchType.LAZY)
//    @JoinColumn
    @JsonBackReference
    private List<ProbationPeriod> probationPeriods;
    @Column(name = "current_report_id")
    private Long currentReportId;

    public Guardian() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
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

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<ProbationPeriod> getProbationPeriods() {
        return probationPeriods;
    }

    public void setProbationPeriods(List<ProbationPeriod> probationPeriods) {
        this.probationPeriods = probationPeriods;
    }

    public Long getCurrentReportId() {
        return currentReportId;
    }

    public void setCurrentReportId(Long currentReportId) {
        this.currentReportId = currentReportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guardian guardian = (Guardian) o;
        return id == guardian.id && chatId == guardian.chatId && age == guardian.age && Objects.equals(username, guardian.username) && Objects.equals(name, guardian.name) && Objects.equals(address, guardian.address) && Objects.equals(phoneNumber, guardian.phoneNumber) && Objects.equals(animals, guardian.animals) && Objects.equals(probationPeriods, guardian.probationPeriods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, username, name, age, address, phoneNumber, animals, probationPeriods);
    }
}
