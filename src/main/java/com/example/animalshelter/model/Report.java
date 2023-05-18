package com.example.animalshelter.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private String entry;
    private String photoId;

    public Report() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && Objects.equals(date, report.date) && Objects.equals(entry, report.entry) && Objects.equals(photoId, report.photoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, entry, photoId);
    }
}
