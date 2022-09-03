package com.example.lmsadminservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomValidationException {
    private int errorCode;
    private String message;
}
