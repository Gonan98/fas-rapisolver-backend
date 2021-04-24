package com.rapisolver.api.exceptions;

public class InternalServerErrorException extends RapisolverException {

    public InternalServerErrorException(String message) {
        super(500, "INTERNAL_SERVER_ERROR", message);
    }
}
