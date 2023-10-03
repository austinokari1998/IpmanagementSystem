package com.example.ipmanagement.Errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class IPNotFoundException extends RuntimeException {
    public IPNotFoundException(String message) {
        super(message);
    }

}
