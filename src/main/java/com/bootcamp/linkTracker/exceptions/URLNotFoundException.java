package com.bootcamp.linkTracker.exceptions;

import org.springframework.http.HttpStatus;

public class URLNotFoundException extends ILinkTrackerException {

    public URLNotFoundException() {
        ErrorDTO error = new ErrorDTO();
        error.setName("URL not found.");
        error.setDescription("The URL dont exist in the database.");
        this.setError(error);
        this.setStatus(HttpStatus.NOT_FOUND);
    }

}
