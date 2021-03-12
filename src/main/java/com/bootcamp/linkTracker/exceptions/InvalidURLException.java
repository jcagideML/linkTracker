package com.bootcamp.linkTracker.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidURLException extends ILinkTrackerException {

    public InvalidURLException() {
        ErrorDTO error = new ErrorDTO();
        error.setName("Invalid URL.");
        error.setDescription("The URL dont have the valid format.");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
