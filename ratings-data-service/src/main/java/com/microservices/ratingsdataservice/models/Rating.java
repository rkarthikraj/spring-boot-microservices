package com.microservices.ratingsdataservice.models;

public class Rating {
    public String movieId;
    public int movieRating;

    public Rating(String movieId, int movieRating) {
        this.movieId = movieId;
        this.movieRating = movieRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }
}
