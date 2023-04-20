package com.example.animalshelter.model;

import java.util.Objects;

public class Cat {

     private int id;
     private String breed;
     private int age;
     private int weight;
     private String name;

     public Cat(int id, String breed, int age, int weight, String name) {
          this.id = id;
          this.breed = breed;
          this.age = age;
          this.weight = weight;
          this.name = name;
     }

     public Cat(String breed, int age, int weight, String name) {
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
          Cat cat = (Cat) o;
          return id == cat.id && age == cat.age && weight == cat.weight && Objects.equals(breed, cat.breed) && Objects.equals(name, cat.name);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, breed, age, weight, name);
     }
}
