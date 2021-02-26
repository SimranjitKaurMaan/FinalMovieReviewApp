package com.company.models;

import com.company.datacontract.UserType;
import com.company.exception.ReviewException;

public class Rating
{
    public static final int RATING_UPPER_BOUND = 10;

    public static final int RATING_LOWER_BOUND = 1;

    public static final int WEIGHTAGE = 2;

    private int totalScore;

    private int criticScore;


    public int getTotalScore() {
        return this.totalScore;
    }

    public int getCriticScore() {
        return this.criticScore;
    }

    public void setRating(int score,UserType userType) throws ReviewException {
        if(score< RATING_LOWER_BOUND || score> RATING_UPPER_BOUND)
        {
            throw new ReviewException("The rating cannot be outside of range "+ RATING_LOWER_BOUND +"-"+ RATING_UPPER_BOUND +".");
        }

        if(userType == UserType.CRITIC)
        {
            score *= WEIGHTAGE;
            this.criticScore += score;
        }
        this.totalScore +=score;
    }
}
