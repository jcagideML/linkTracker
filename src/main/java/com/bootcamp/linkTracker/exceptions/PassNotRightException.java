package com.bootcamp.linkTracker.exceptions;

import org.springframework.http.HttpStatus;

public class PassNotRightException extends ILinkTrackerException {

    public PassNotRightException() {
        ErrorDTO error = new ErrorDTO();
        error.setName("Wrong Password.");
        error.setDescription("The password is worong.");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
