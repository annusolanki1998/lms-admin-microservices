package com.example.lmsadminservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Purpose : AdminNotFoundException is used to handle the exceptions
 * Version : 1.0
 * @author : Annu kumari
 * */

@ResponseStatus
public class AdminNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public AdminNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
