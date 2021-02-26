package com.company.exception;


/**
 * Generic exception for general review service exceptions.
 */
public class ReviewException extends Exception {

    public ReviewException(String message)
    {
        super(message);
    }
}
