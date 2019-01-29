package com.stackroute.muzixApp.exceptions;

public class TrackAlreadyExistsException extends Exception {

    private String message;

    public TrackAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }
}

