package com.moviebooking.model;

public class Cinema {
    private int id;
    private String name;
    private String location;
    private String city;

    public Cinema() {}

    public Cinema(int id, String name, String location, String city) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.city = city;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}