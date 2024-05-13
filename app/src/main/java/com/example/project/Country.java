package com.example.project;

public class Country {
    private String id;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;

    public Country(String id, String name, String type, String company, String location, String category, int size, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
    }

    public int getCost(){return cost;}
    @Override
    public String toString() {
        return name;
    }
}