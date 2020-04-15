package com.tabit.dcm2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The Guest is already checked in.")
public class GuestIllegalStateException extends RuntimeException {
    public GuestIllegalStateException() {
        super();
    }
}
