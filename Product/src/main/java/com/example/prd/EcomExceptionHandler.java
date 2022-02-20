package com.example.prd;

import com.example.prd.exce.NoProductFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EcomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {NoProductFound.class})
    protected ResponseEntity<Object> handleProductNotFoundException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Product is not available";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
