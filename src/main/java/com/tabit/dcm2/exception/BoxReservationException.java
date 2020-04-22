package com.tabit.dcm2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The box number is already used.")
public class BoxReservationException extends RuntimeException {
    public BoxReservationException() {
        super();
    }
}
