package com.example.animalshelter.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "probation_period")
public class ProbationPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "probation_period_id")
    private Long id;
    @Column
    private LocalDate ends;
    @Column
    private boolean wasSuccessful;
    @Column
    private String volunteersComment;
    @Column
    private boolean needToSendVolunteersComment;
    @OneToOne
//    @Column(insertable=false, updatable=false)
    @JoinColumn(name = "probation_period_id", nullable = false)
    @JsonManagedReference
    private Animal animal;
    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(insertable=false, updatable=false)
    @JoinColumn(name = "probation_period_id", nullable = false,insertable=false, updatable=false)
    @JsonManagedReference
    private Guardian guardian;
    @OneToMany(mappedBy = "probationPeriod", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Report> reports;


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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProbationPeriod that = (ProbationPeriod) o;
        return id == that.id && wasSuccessful == that.wasSuccessful && needToSendVolunteersComment == that.needToSendVolunteersComment && Objects.equals(ends, that.ends) && Objects.equals(volunteersComment, that.volunteersComment) && Objects.equals(animal, that.animal) && Objects.equals(guardian, that.guardian) && Objects.equals(reports, that.reports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ends, wasSuccessful, volunteersComment, needToSendVolunteersComment, animal, guardian, reports);
    }
}
