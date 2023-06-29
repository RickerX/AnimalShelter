package com.example.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "dog_message")
public class DogMessage {
    @Id
    private String tag;
    private String text;

    public DogMessage() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogMessage that = (DogMessage) o;
        return Objects.equals(tag, that.tag) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, text);
    }
}
