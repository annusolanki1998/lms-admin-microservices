package com.example.lmsadminservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AdminDTO {
    /*
     * Purpose : AdminDTO are used to create and update admins
     * Version : 1.0
     * @author : Annu Kumari
     * */

    @NotNull(message = "FirstName should not be empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Firstname should start with capital letter and  atleast min of three character")
    private String firstName;

    @NotNull(message = "LastName should not be empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Lastname should start with capital letter and  atleast min of three character")
    private String lastName;

    @NotNull(message = "Mobilenumber should not be empty")
    @Pattern(regexp = "[+]91 [6-9]\\d{9}", message = "Invalid mobile number")
    private String mobileNumber;

    @NotNull(message = "Email id should not be empty")
    @Pattern(regexp = "[a-z][A-Z a-z 0-9]+[@][a-z]+[.][a-z]{2,}", message = "Invalid email id")
    private String emailId;

    @NotNull(message = "Password should not be empty")
    @Pattern(regexp = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}", message = "Invalid password")
    private String password;

    @NotNull(message = "Status should not be empty")
    private String status;


}

