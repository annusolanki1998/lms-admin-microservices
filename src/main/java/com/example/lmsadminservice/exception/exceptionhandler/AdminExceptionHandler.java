package com.example.lmsadminservice.exception.exceptionhandler;

import com.example.lmsadminservice.exception.AdminNotFoundException;
import com.example.lmsadminservice.exception.CustomValidationException;
import com.example.lmsadminservice.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<Response> handlerHiringException(AdminNotFoundException exception) {
        Response response = new Response();
        response.setErrorCode(400);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Using custom exception for handling the error of validation part
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomValidationException> customValidationException(MethodArgumentNotValidException exception) {
        CustomValidationException customValidationException = new CustomValidationException();
        customValidationException.setErrorCode(400);
        customValidationException.setMessage(exception.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(customValidationException, HttpStatus.BAD_REQUEST);
    }


}
