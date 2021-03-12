package com.bootcamp.linkTracker.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class ILinkTrackerException extends Exception {

    private ErrorDTO error;
    private HttpStatus status;

}
