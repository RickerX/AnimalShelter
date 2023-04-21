package com.example.animalshelter.model;

import java.util.Objects;

public class Cat {

     private int id;
     private String breed;
     private float age;
     private float weight;
     private String name;
     private String gender;

     public Cat(int id, String breed, float age, float weight, String name,String gender) {
          this.id = id;
          this.breed = breed;
          this.age = age;
          this.weight = weight;
          this.name = name;
          this.gender = gender;
     }

     public Cat(String breed, int age, int weight, String name,String gender) {
          this.breed = breed;
          this.age = age;
          this.weight = weight;
          this.name = name;
          this.gender = gender;
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

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Cat cat = (Cat) o;
          return id == cat.id && Float.compare(cat.age, age) == 0 && Float.compare(cat.weight, weight) == 0 && Objects.equals(breed, cat.breed) && Objects.equals(name, cat.name) && Objects.equals(gender, cat.gender);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, breed, age, weight, name, gender);
     }
}
