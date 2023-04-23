package com.example.animalshelter.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "breed_dog")
    private String breed;
    @Column(name = "age_dog")
    private float age;
    @Column(name = "weight_dog")
    private float weight;
    @Column(name = "nickname_dog")
    private String name;
    @Column(name = "gender_dog")
    private String gender;

    public Dog(int id, String breed, float age, float weight, String name, String gender) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
    }
    public Dog() {
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

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id && Float.compare(dog.age, age) == 0 && Float.compare(dog.weight, weight) == 0 && Objects.equals(breed, dog.breed) && Objects.equals(name, dog.name) && Objects.equals(gender, dog.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, age, weight, name, gender);
    }
}
