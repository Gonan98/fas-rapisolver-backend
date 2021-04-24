package com.rapisolver.api.exceptions;

public class NotFoundException extends RapisolverException {

    public NotFoundException(String message) {
        super(404, "NOT_FOUND", message);
    }
}
