package com.company.models;

import com.company.datacontract.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class User
{
    private static final AtomicInteger count = new AtomicInteger(0);

    /**
     * The id of the user.
     */
    private final Integer userId;

    /**
     * The name of the user.
     */
    private final String userName;

    /**
     * The type of user.
     */
    private UserType userType;

    /**
     * The list of movie names for which user has given reviews.
     */
    private List<String> reviewsGiven;


    /**
     * Initialises the User object and sets default values for the fields.
     * @param userName user name.
     */
    public User(String userName)
    {
        this.userId = count.incrementAndGet();
        this.userName = userName;
        this.userType = UserType.VIEWER;
        this.reviewsGiven = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUserName()
    {
        return userName;
    }

    public Integer getReviewCount()
    {
        return reviewsGiven.size();
    }

    public void setReviewGiven(String movieName)
    {
        this.reviewsGiven.add(movieName);
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }
}
