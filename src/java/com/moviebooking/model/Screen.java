package com.moviebooking.model;

public class Screen {
    private int id;
    private int cinemaId;
    private String screenName;
    private int capacity;

    public Screen() {}

    public Screen(int id, int cinemaId, String screenName, int capacity) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.screenName = screenName;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCinemaId() { return cinemaId; }
    public void setCinemaId(int cinemaId) { this.cinemaId = cinemaId; }

    public String getScreenName() { return screenName; }
    public void setScreenName(String screenName) { this.screenName = screenName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}