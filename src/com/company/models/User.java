package com.company.models;

import com.company.datacontract.UserType;

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
     * The count of reviews given by the user.
     */
    private Integer reviewCount;


    /**
     * Initialises the User object and sets default values for the fields.
     * @param userName user name.
     */
    public User(String userName)
    {
        this.userId = count.incrementAndGet();
        this.userName = userName;
        this.userType = userType.VIEWER;
        this.reviewCount = 0;
    }

    public int getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
