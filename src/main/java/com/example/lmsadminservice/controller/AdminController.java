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

    /*
     * Purpose : Welcome to admin service
     * @author : Annu kumari
     * */

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to LMS Spring application project";
    }

    /*
     * Purpose : Create admin details
     * @author : Annu Kumari
     * @Param : adminDTO and token
     * */


    @PostMapping("/addadmin")
    public ResponseEntity<ResponseUtil> addAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        ResponseUtil responseUtil = adminService.addAdmin(adminDTO);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Update existing admin details by using id
     * @author : Annu Kumari
     * @Param :  id,adminDTO,and token
     * */

    @PutMapping("/updateadmin/{id}")
    public ResponseEntity<ResponseUtil> updateAdmin(@Valid @PathVariable Long id,
                                                    @RequestBody AdminDTO adminDTO,
                                                    @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.updateAdmin(id, adminDTO, token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Retrieve all admin details
     * @author : Annu Kumari
     * @Param :  token
     * */

    @GetMapping("/getadmins")
    public ResponseEntity<List<?>> getAdmins(@RequestHeader String token) {
        List<AdminModel> responseUtil = adminService.getAdmins(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    /*
     * Purpose : Delete existing admin details by using id
     * @author : Annu Kumari
     * @Param :  id and token
     * */


    @DeleteMapping("deleteadmin/{id}")
    public ResponseEntity<ResponseUtil> deleteAdmin(@PathVariable Long id,
                                                    @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.deleteAdmin(id, token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    /*
     * Purpose : Retrieve existing admin details by using id
     * @author : Annu Kumari
     * @Param :  id and token
     * */

    @GetMapping("getadmin/{id}")
    public ResponseEntity<ResponseUtil> getAdmin(@PathVariable Long id,
                                                 @RequestHeader String token) {
        ResponseUtil responseUtil = adminService.getAdmin(id, token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    /*
     * Purpose : Create token
     * @author : Annu Kumari
     * @Param : emailId and password
     * */


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestParam String emailId,
                                          @RequestParam String password) {
        Response response = adminService.login(emailId, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Update password
     * @author : Annu Kumari
     * @Param :  token and password
     * */

    @PutMapping("/changepassword")
    public ResponseEntity<ResponseUtil> updatePassword(@RequestHeader String token,
                                                       @RequestParam String password) {
        ResponseUtil responseUtil = adminService.updatePassword(token, password);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Reset password
     * @author : Annu Kumari
     * @Param :  emailId
     * */

    @PutMapping("/resetpassword")
    public ResponseEntity<ResponseUtil> resetPassword(@RequestParam String emailId) {
        ResponseUtil responseUtil = adminService.resetPassword(emailId);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Create profile path
     * @author : Annu Kumari
     * @Param :  id,token and profilepath
     * */

    @PostMapping("/addprofilepath")
    public ResponseEntity<ResponseUtil> addProfilePath(@RequestParam Long id,
                                                       @RequestHeader String token,
                                                       @RequestParam String profilePath) {
        ResponseUtil responseUtil = adminService.addProfilePath(id, token, profilePath);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Get validate user
     * @author : Annu Kumari
     * @Param :  token
     * */

    @GetMapping("/validate/{token}")
    public Boolean validate(@PathVariable String token) {
        return adminService.validate(token);
    }
}

