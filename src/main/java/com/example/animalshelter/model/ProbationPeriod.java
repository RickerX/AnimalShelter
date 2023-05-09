package com.example.animalshelter.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class ProbationPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate ends;
    private boolean wasSuccessful;
    private String volunteersComment;
    private boolean needToSendVolunteersComment;

    public ProbationPeriod() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getEnds() {
        return ends;
    }

    public void setEnds(LocalDate ends) {
        this.ends = ends;
    }

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public String getVolunteersComment() {
        return volunteersComment;
    }

    public void setVolunteersComment(String volunteersComment) {
        this.volunteersComment = volunteersComment;
    }

    public boolean isNeedToSendVolunteersComment() {
        return needToSendVolunteersComment;
    }

    public void setNeedToSendVolunteersComment(boolean needToSendVolunteersComment) {
        this.needToSendVolunteersComment = needToSendVolunteersComment;
    }

//    public Animal getAnimal() {
//        return animal;
//    }
//
//    public void setAnimal(Animal animal) {
//        this.animal = animal;
//    }
//
//    public Guardian getGuardian() {
//        return guardian;
//    }
//
//    public void setGuardian(Guardian guardian) {
//        this.guardian = guardian;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProbationPeriod that = (ProbationPeriod) o;
        return id == that.id && wasSuccessful == that.wasSuccessful && needToSendVolunteersComment == that.needToSendVolunteersComment && Objects.equals(ends, that.ends) && Objects.equals(volunteersComment, that.volunteersComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ends, wasSuccessful, volunteersComment, needToSendVolunteersComment);
    }
}
