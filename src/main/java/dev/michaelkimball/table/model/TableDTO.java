package dev.michaelkimball.table.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableDTO {
    public String id;
    public String name;
    public List<Item> items;
    public TableDTO withId(String id){
        this.id = id;
        return this;
    }
    public TableDTO withName(String name){
        this.name = name;
        return this;
    }
    public TableDTO withItems(List<Item> items){
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "TableDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
