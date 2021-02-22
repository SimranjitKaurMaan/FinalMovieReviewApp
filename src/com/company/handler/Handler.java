package com.company.handler;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class Handler implements Thread.UncaughtExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.error("Exception : " + e.getMessage());
    }
}
