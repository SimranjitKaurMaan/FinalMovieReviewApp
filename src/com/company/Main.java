package com.company;

import com.company.exception.ReviewException;
import com.company.handler.Handler;
import com.company.helper.Helper;
import com.company.services.MovieService;
import com.company.services.ReviewService;
import com.company.services.UserService;


import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Main {

        public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static void main(String[] args)  {
            Handler globalExceptionHandler = new Handler();
            Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);

            try {
                    // Sample inputs for the program
                    UserService userService = Helper.createUserService();
                    userService.addUser("SRK");
                    userService.addUser("Salman");
                    userService.addUser("Deepika");

                    MovieService movieService = Helper.createMovieService();
                    movieService.addMovie("Don", 2006, Arrays.asList("Action", "Comedy"));
                    movieService.addMovie("Tiger", 2008, Arrays.asList("Action"));
                    movieService.addMovie("Padmavat", 2006, Arrays.asList("Drama"));
                    movieService.addMovie("LunchBox", 2021, Arrays.asList("Drama"));
                    movieService.addMovie("Guru", 2006, Arrays.asList("Drama"));
                    movieService.addMovie("Metro", 2006, Arrays.asList("Romance"));
                    movieService.addMovie("Harry Potter", 2001, Arrays.asList("Comedy"));

                    ReviewService reviewService = Helper.createReviewService(userService, movieService);
                    reviewService.addReview("SRK", "Don", 2);
                    reviewService.addReview("SRK", "Padmavat", 8);
                    reviewService.addReview("SRK", "Tiger", 5);
                    reviewService.addReview("Salman", "Don", 5);
                    reviewService.addReview("Salman", "Tiger", 3);
                    reviewService.addReview("Salman", "Padmavat", 8);
                    reviewService.addReview("Salman", "Guru", 5);
                    reviewService.addReview("Deepika", "Don", 9);
                    reviewService.addReview("Deepika", "Guru", 6);
                    reviewService.addReview("Deepika", "Metro", 5);
                    reviewService.addReview("Deepika", "Padmavat", 3);

                    Double reviewScoreByMovie = reviewService.getAverageReviewScoreByMovie("Don");
                    System.out.println("===============================OUTPUT================================");
                    System.out.println("Don movie average review score: " + DECIMAL_FORMAT.format(reviewScoreByMovie));
                    System.out.println("======================================================================");
                    List<String> topMovies = reviewService.getTopMoviesByCriticsByGenre("Drama", 2);
                    System.out.println("Top 2 movies by Genre Drama by Critics :");
                    for (String movie : topMovies) {
                            System.out.println(movie);
                    }
                    System.out.println("=======================================================================");
                    Double reviewScoreByYear = reviewService.getAverageReviewScoreByYear(2006);
                    System.out.println("Movie review score for the year 2006: " + DECIMAL_FORMAT.format(reviewScoreByYear));
                    System.out.println("===============================END=====================================");
                    //sample inputs to test negative scenarios

                    //userService.addUser(null);
                    //userService.addUser("");
                    //movieService.addMovie("Hell Boy",2007,null);
                    //reviewService.addReview("", "Metro", 2);
                    //reviewService.addReview("Salman", null, 3);
                    //reviewService.addReview("Salman", "Metro", 11);
                    //reviewService.addReview("Salman", "Tiger", 3);
                    //reviewService.addReview("Salman", "LunchBox", 9);
                    //Double reviewScoreByInvalidYear = reviewService.getAverageReviewScoreByYear(2021);
                    //List<String> topInvalidCountMovies = reviewService.getTopMoviesByCriticsByGenre("Drama", 20);
            } catch (IllegalArgumentException ex)
            {
                    System.out.println("Exception1: "+ex);
            }
            catch (NullPointerException ex)
            {
                    System.out.println("Exception2: "+ex);

            }catch (ReviewException ex)
            {
                    System.out.println("Exception3: "+ex);
            }
            catch(Exception ex)
            {
                    System.out.println("Exception4: "+ex);
            }
    }
}

