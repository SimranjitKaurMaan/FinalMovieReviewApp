package com.company.services;

import com.company.abstractions.IMovieService;
import com.company.datacontract.Genre;
import com.company.helper.Helper;
import com.company.models.Movie;
import com.company.utilities.Arg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MovieService implements IMovieService
{

    private final Map<String, Movie> movies;

    public MovieService()
    {
        movies = new HashMap<>();
    }

    /**
     * Adds the movie.
     * @param name movie name.
     * @param year year of release.
     * @param genres list of genres which the movie belongs to.
     */
    @Override
    public void addMovie(String name, Integer year, List<String> genres) {
        Arg.isNotNullOrWhiteSpace(name);
        Arg.isNotNull(genres);

        List<Genre> movieGenres = new LinkedList<>();
        genres.forEach((String genre) -> {
            Genre movieGenre = Genre.valueOf(genre);
            movieGenres.add(movieGenre);
        });

        /**
        Movie movie = new Movie(name, year, movieGenres, this.cleaner); // if this has functions then it wont be testable.
         **/
        Movie movie = Helper.createMovie(name,year,movieGenres);
        movies.put(name,movie);
    }

    /**
     *  Gets the movies.
     * @return Map of the movies.
     */
    @Override
    public Map<String, Movie> getMovies() {
        return this.movies;
    }
}
