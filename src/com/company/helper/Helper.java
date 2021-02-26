package com.company.helper;

import com.company.datacontract.Genre;
import com.company.models.Movie;
import com.company.models.Rating;
import com.company.models.User;
import com.company.services.MovieService;
import com.company.services.ReviewService;
import com.company.services.UserService;

import java.util.List;

public class Helper
{
    public static Movie createMovie(String name, Integer year, List<Genre> movieGenres)
    {
        Rating rating = new Rating();
        return new Movie(name,year,movieGenres,rating);
    }

    public static User createUser(String userName)
    {
        return new User(userName);
    }

    public static UserService createUserService()
    {
        return new UserService();
    }

    public static MovieService createMovieService()
    {
        return new MovieService();
    }

    public static ReviewService createReviewService(UserService userService, MovieService movieService)
    {
        return new ReviewService(userService, movieService);
    }
}
