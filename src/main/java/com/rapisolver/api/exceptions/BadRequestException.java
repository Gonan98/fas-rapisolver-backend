package com.rapisolver.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RapisolverException {

    public BadRequestException(String message) {
        super(400, "BAD_REQUEST", message);
    }
}
