package com.explorertech.entities;

import java.util.Date;

public class Movies {

    private String movieName;
    private Date releaseDate;
    private double rating;

    public Movies(String movieName, Date releaseDate, double rating) {
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Movies() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
