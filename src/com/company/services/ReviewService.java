package com.company.services;

import com.company.abstractions.IMovieService;
import com.company.abstractions.IReviewService;
import com.company.abstractions.IUserService;
import com.company.datacontract.Genre;
import com.company.datacontract.UserType;
import com.company.exception.ReviewException;
import com.company.models.Movie;
import com.company.models.User;
import com.company.utilities.Arg;



import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class ReviewService implements IReviewService
{
    public static final int RATING_UPPER_BOUND = 10;

    public static final int RATING_LOWER_BOUND = 1;

    public static final int WEIGHTAGE = 2;

    private IUserService userService;

    private IMovieService movieService;

    private Map<String, List<Integer>> movieUserMapping;

    /**
     *  Constructor for the Review Service.
     * @param userService Injects the user service.
     * @param movieService Injects the movie service.
     */
    public ReviewService(IUserService userService, IMovieService movieService)
    {
        this.userService = userService;
        this.movieService = movieService;
        this.movieUserMapping = new HashMap<>();
    }

    /**
     * Adds Movie Review.
     * @param userName user name.
     * @param movieName movie name.
     * @param movieRating rating given to the movie.
     */
    @Override
    public void addReview(String userName, String movieName, Integer movieRating)
    {
        Arg.isNotNullOrWhiteSpace(userName);
        Arg.isNotNullOrWhiteSpace(movieName);

        if(movieRating< RATING_LOWER_BOUND || movieRating> RATING_UPPER_BOUND)
        {
            throw new ReviewException("The rating cannot be outside of range "+ RATING_LOWER_BOUND +"-"+ RATING_UPPER_BOUND +".");
        }

        Map<String, User> users = this.userService.getUsers();
        Map<String, Movie> movies = this.movieService.getMovies();

        movieUserMapping.putIfAbsent(movieName, new LinkedList<>());
        Integer userId = users.get(userName).getUserId();
        if (movieUserMapping.get(movieName).contains(userId))
        {
            throw new ReviewException("The User " + userName + " has already reviewed Movie "+ movieName+" .Multiple reviews not allowed.");

        }else if (movies.get(movieName).getReleaseYear() >= Year.now().getValue())
        {
            throw new ReviewException("The User "+userName+ " cannot review upcoming Movie "+ movieName +".The movie "+movieName+" is yet to be released.");
        }
        movieUserMapping.get(movieName).add(userId);
        User user = users.get(userName);
        int reviewCount = user.getReviewCount()+1;
        user.setReviewCount(reviewCount);
        if (reviewCount > 3)
        {
            user.setUserType(UserType.CRITIC);
            movieRating = movieRating * WEIGHTAGE;
        }

        Integer movieReviewScore = movies.get(movieName).getReviewScore() + movieRating;
        movies.get(movieName).setReviewScore(movieReviewScore);
        if (user.getUserType()==UserType.CRITIC)
        {
            Integer movieCriticReviewScore = movies.get(movieName).getCriticReviewScore()+movieRating;
            movies.get(movieName).setCriticReviewScore(movieCriticReviewScore);
        }
    }

    /**
     * Gets average review score for a particular movie.
     * @param movieName name of the movie.
     * @return ReviewScore.
     */
    @Override
    public Double getAverageReviewScoreByMovie(String movieName)
    {
        Arg.isNotNullOrWhiteSpace(movieName);

        Map<String,Movie> movies = this.movieService.getMovies();
        Integer usersCount;
        try
        {
            usersCount = movieUserMapping.get(movieName).size();

        }catch (NullPointerException ex)
        {
            throw new ReviewException("No users reviewed movie "+movieName);
        }
        Integer reviewScore = movies.values()
                .stream()
                .filter(movie -> movie.getMovieName().equals(movieName))
                .map(Movie::getReviewScore)
                .findAny()
                .orElse(0);

        return (reviewScore*1.0) / usersCount;
    }


    /**
     * Gets average review score in a particular year of release.
     * @param releaseYear year of release.
     * @return Review Score.
     */
    @Override
    public Double getAverageReviewScoreByYear(Integer releaseYear)  {
        if(releaseYear>=Year.now().getValue())
        {
            throw new ReviewException("The release year cannot be current or upcoming year.");
        }

        Map<String,Movie> movies = this.movieService.getMovies();
        List<Integer> reviewScores = movies.values()
                .stream()
                .filter(movie -> movie.getReleaseYear().equals(releaseYear))
                .map(Movie::getReviewScore)
                .collect(Collectors.toList());

        Integer moviesCount = reviewScores.size();
        Double totalReviewScore = reviewScores.stream().reduce(0,Integer::sum)*1.0;
        if(moviesCount == 0)
        {
            throw new ReviewException("No movies reviewed in year "+releaseYear);
        }

        return totalReviewScore/moviesCount;
    }


    /**
     * List top n movies by total review score by critics in a particular genre.
     * @param genre Genre.
     * @param count count of top movies.
     * @return List of top movie names.
     */
    @Override
    public List<String> getTopMoviesByCriticsByGenre(String genre, Integer count)
    {
        Arg.isNotNull(genre);

        Genre movieGenre = Genre.valueOf(genre);
        Map<String,Movie> movies = this.movieService.getMovies();
        if (count <= 0 || count > movies.size())
        {
            throw new ReviewException("The parameter count "+ count+" cannot be more than the number of movies entered.");
        }
        List<String> filteredMovies = movies.values()
                .stream()
                .filter(movie -> movie.getGenres().contains(movieGenre))
                .sorted(Comparator.comparing(Movie::getCriticReviewScore).reversed())
                .limit(count).map(Movie::getMovieName).collect(Collectors.toList());


        return filteredMovies;
    }
}
