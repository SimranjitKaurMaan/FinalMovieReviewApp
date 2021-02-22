package com.company.exception;


/**
 * Generic exception for general review service exceptions.
 */
public class ReviewException extends RuntimeException {

    public ReviewException(String message) {
        super(message);
    }
}
