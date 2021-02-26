package com.company.abstractions;

import com.company.exception.ReviewException;

import java.util.List;


/**
 * Interface for the Review Service.
 */
public interface IReviewService
{

    /**
     * Adds the Movie Review.
     * @param userName name of the user
     * @param movieName name of the movie
     * @param movieRating rating given to the movie
     */
    void addReview(String userName, String movieName, Integer movieRating) throws ReviewException;


    /**
     * Gets average review score for a particular movie.
     * @param movieName name of the movie
     * @return ReviewScore of the movie.
     */
    Double getAverageReviewScoreByMovie(String movieName) throws ReviewException;

    /**
     * Gets average review score in a particular year of release.
     * @param releaseYear year of release.
     * @return Review Score.
     */
    Double getAverageReviewScoreByYear(Integer releaseYear) throws ReviewException;


    /**
     * List top n movies by total review score by critics in a particular genre.
     * @param genre Genre.
     * @param count top n movies.
     * @return List of top movie names.
     */
    List<String> getTopMoviesByCriticsByGenre(String genre, Integer count) throws ReviewException;
}