package dev.michaelkimball.table.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String id;
    private String description;
    private int weight;
    private int position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Item withId(String id) {
        this.id = id;
        return this;
    }

    public Item withDescription(String description) {
        this.description = description;
        return this;
    }

    public Item withWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public Item withPosition(int position) {
        this.position = position;
        return this;
    }
}
