package com.example.prd.exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoProductFound extends RuntimeException{

    public NoProductFound() {
    }

    public NoProductFound(String message) {
        super(message);
    }

    public NoProductFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProductFound(Throwable cause) {
        super(cause);
    }

    public NoProductFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
