package com.example.project;

public class Country {
    private String id;
    private String name;
    private String type;
    private String location;
    private int size;
    private int cost;
    private String wiki;
    private String imgUrl;

    public Country(String id, String name, String type, String location, int size, int cost, String wiki, String imgUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.size = size;
        this.cost = cost;
        this.wiki = wiki;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return name;
    }
}