package com.company.abstractions;

import com.company.models.Movie;
import java.util.List;
import java.util.Map;


/**
 * Interface for the Movie Service.
 */
public interface IMovieService
{
    void addMovie(String name, Integer year, List<String> genres);

    Map<String, Movie> getMovies();
}
