package com.example.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private long id;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private float age;
    @Column(name = "weight")
    private float weight;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "color")
    private String color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id",nullable = false,insertable=false, updatable=false)
    @JsonManagedReference
    private Guardian guardian;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false,insertable=false, updatable=false)
    @JsonManagedReference
    private Shelter shelter;
    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
    @JsonBackReference
    private ProbationPeriod probationPeriod;

    public Animal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public ProbationPeriod getProbationPeriod() {
        return probationPeriod;
    }

    public void setProbationPeriod(ProbationPeriod probationPeriod) {
        this.probationPeriod = probationPeriod;
    }
    public void setClient(Guardian guardian) {
        this.guardian = guardian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && Float.compare(animal.age, age) == 0 && Float.compare(animal.weight, weight) == 0 && Objects.equals(breed, animal.breed) && Objects.equals(nickname, animal.nickname) && Objects.equals(gender, animal.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, age, weight, nickname, gender);
    }
}
