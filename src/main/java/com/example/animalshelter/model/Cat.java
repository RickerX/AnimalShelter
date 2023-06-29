package com.example.animalshelter.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "cat")
public class Cat {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "cat_id")
     private int id;
     @Column(name = "breed_cat")
     private String breed;
     @Column(name = "age_cat")
     private float age;
     @Column(name = "weight_cat")
     private float weight;
     @Column(name = "nickname_cat")
     private String name;
     @Column(name = "gender_cat")
     private String gender;
     public Cat(){}
     public Cat(int id, String breed, float age, float weight, String name,String gender) {
          this.id = id;
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
