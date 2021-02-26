package com.company.models;

import com.company.datacontract.Genre;
import com.company.datacontract.UserType;
import com.company.exception.ReviewException;

import java.util.List;

public class Movie
{
    private final String movieName;
    private final Integer releaseYear;
    private final List<Genre> genres;
    private Rating rating;

    public Movie(String movieName, Integer releaseYear, List<Genre> genres,Rating rating)
    {
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
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
        return this.rating.getTotalScore();
    }

    public Integer getCriticReviewScore()
    {
        return this.rating.getCriticScore();
    }

    public void setReviewScore(Integer reviewScore,UserType userType) throws ReviewException {
        this.rating.setRating(reviewScore,userType);
    }
}
