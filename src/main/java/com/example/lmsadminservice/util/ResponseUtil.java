package com.example.lmsadminservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Purpose : ResponseUtil used to handle the exception
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUtil {
    private int statusCode;
    private String statusMessage;
    private Object object;
}
