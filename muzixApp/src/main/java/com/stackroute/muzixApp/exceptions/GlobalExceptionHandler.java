package com.stackroute.muzixApp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//This class is for for global exception handling

@ControllerAdvice
public class GlobalExceptionHandler {

    //This Exception Handler is for Track already exits exception
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity userAlreadyExistsException(final TrackAlreadyExistsException e) {
        return  new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
    }

    //This Exception Handler is track not exists exception
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity trackDoesNotExistException(final TrackNotFoundException e) {
        return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
