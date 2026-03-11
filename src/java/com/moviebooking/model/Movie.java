package com.moviebooking.model;

public class Movie {
    private int id;
    private String name;
    private String genre;
    private int duration;
    private String language;
    private double rating;

    public Movie() {}

    public Movie(int id, String name, String genre, int duration, String language, double rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.language = language;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}