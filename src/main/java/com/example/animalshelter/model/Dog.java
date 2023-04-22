package com.example.animalshelter.model;

import java.util.Objects;

public class Dog {

    private int id;

    private String breed;
    private int age;
    private int weight;
    private String name;

    public Dog(int id, String breed, int age, int weight, String name) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id && age == dog.age && weight == dog.weight && Objects.equals(breed, dog.breed) && Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, age, weight, name);
    }
}
