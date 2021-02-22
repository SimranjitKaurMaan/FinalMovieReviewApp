package com.company.models;

import com.company.datacontract.Genre;

import java.util.List;

public class Movie
{
    private final String movieName;
    private final Integer releaseYear;
    private final List<Genre> genres;
    private Integer reviewScore;
    private Integer criticReviewScore;

    public Movie(String movieName, Integer releaseYear, List<Genre> genres)
    {
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.reviewScore =0;
        this.criticReviewScore =0;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public Integer getCriticReviewScore() {
        return criticReviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public void setCriticReviewScore(Integer criticReviewScore) {
        this.criticReviewScore = criticReviewScore;
    }

}
