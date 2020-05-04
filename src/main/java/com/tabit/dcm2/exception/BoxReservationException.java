package com.tabit.dcm2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The box number is already used.")
public class BoxReservationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BoxReservationException(Throwable cause) {
        super(cause);
    }
}
