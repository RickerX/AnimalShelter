package com.example.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class InfoMessage {
    @Id
    private String tag;
    private String text;

    public InfoMessage() {
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
        InfoMessage that = (InfoMessage) o;
        return Objects.equals(tag, that.tag) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, text);
    }
}
