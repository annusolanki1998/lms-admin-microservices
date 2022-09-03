package com.example.lmsadminservice.controller;


import com.example.lmsadminservice.dto.AdminDTO;
import com.example.lmsadminservice.model.AdminModel;
import com.example.lmsadminservice.service.IAdminService;
import com.example.lmsadminservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AdminModel addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        return adminService.addAdmin(adminDTO);
    }


    @PutMapping("/updateadmin/{id}")
    public AdminModel updateAdmin(@Valid @PathVariable Long id, @RequestBody AdminDTO adminDTO, @RequestHeader String token) {
        return adminService.updateAdmin(id, adminDTO, token);
    }

    @GetMapping("/getadmins")
    public List<AdminModel> getAdmins(@RequestHeader String token) {
        return adminService.getAdmins(token);
    }


    @DeleteMapping("deleteadmin/{id}")
    public AdminModel deleteAdmin(@PathVariable Long id, @RequestHeader String token) {
        return adminService.deleteAdmin(id, token);
    }

    @GetMapping("getadmin/{id}")
    public AdminModel getAdmin(@PathVariable Long id, @RequestHeader String token) {
        return adminService.getAdmin(id, token);
    }


    @PostMapping("/login")
    public Response login(@RequestParam String emailId, @RequestParam String password) {
        return adminService.login(emailId, password);
    }

    @PutMapping("/changepassword")
    public AdminModel changePassword(@RequestHeader String token, @RequestParam String password) {
        return adminService.changePassword(token, password);
    }

    @PutMapping("/resetpassword")
    public AdminModel resetPassword(@RequestParam String emailId) {
        return adminService.resetPassword(emailId);
    }
}
