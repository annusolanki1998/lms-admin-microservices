package com.example.lmsadminservice.controller;


import com.example.lmsadminservice.dto.AdminDTO;
import com.example.lmsadminservice.model.AdminModel;
import com.example.lmsadminservice.service.IAdminService;
import com.example.lmsadminservice.util.Response;
import com.example.lmsadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to LMS Spring application project";
    }


    @PostMapping("/addadmin")
    public ResponseEntity<ResponseUtil> addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        ResponseUtil responseUtil = adminService.addAdmin(adminDTO);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }


    @PutMapping("/updateadmin/{id}")
    public ResponseEntity<ResponseUtil> updateAdmin(@Valid @PathVariable Long id,
                                  @RequestBody AdminDTO adminDTO,
                                  @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.updateAdmin(id,adminDTO,token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @GetMapping("/getadmins")
    public ResponseEntity<List<?>> getAdmins(@RequestHeader String token) {
        List<AdminModel> responseUtil = adminService.getAdmins(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }


    @DeleteMapping("deleteadmin/{id}")
    public ResponseEntity<ResponseUtil> deleteAdmin(@PathVariable Long id,
                                  @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.deleteAdmin(id,token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    @GetMapping("getadmin/{id}")
    public ResponseEntity<ResponseUtil> getAdmin(@PathVariable Long id,
                                                 @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.getAdmin(id,token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestParam String emailId,
                          @RequestParam String password) {
        Response response = adminService.login(emailId,password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<ResponseUtil> updatePassword(@RequestHeader String token,
                                                       @RequestParam String password) {
        ResponseUtil responseUtil = adminService.updatePassword(token,password);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @PutMapping("/resetpassword")
    public ResponseEntity<ResponseUtil> resetPassword(@RequestParam String emailId) {
        ResponseUtil responseUtil = adminService.resetPassword(emailId);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

        @PostMapping("/addprofilepath")
        public ResponseEntity<ResponseUtil> addProfilePath(@RequestParam Long id,
                                                           @RequestHeader String token,
                                                           @RequestParam String profilePath) {
            ResponseUtil responseUtil= adminService.addProfilePath(id, token,profilePath);
            return new ResponseEntity<>(responseUtil,HttpStatus.OK);
        }

        @GetMapping("/validate/{token}")
        public Boolean validate(@PathVariable String token) {
            return adminService.validate(token);
        }
    }

