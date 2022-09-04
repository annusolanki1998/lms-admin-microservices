package com.example.lmsadminservice.service;


import com.example.lmsadminservice.dto.AdminDTO;
import com.example.lmsadminservice.model.AdminModel;
import com.example.lmsadminservice.util.Response;
import com.example.lmsadminservice.util.ResponseUtil;


import java.util.List;

public interface IAdminService {


    ResponseUtil addAdmin(AdminDTO adminDTO);

    ResponseUtil updateAdmin(Long id, AdminDTO adminDTO, String token);

    List<AdminModel> getAdmins(String token);

    ResponseUtil deleteAdmin(Long id, String token);

    ResponseUtil getAdmin(Long id, String token);

    Response login(String emailId, String password);

    ResponseUtil updatePassword(String token, String password);

    ResponseUtil resetPassword(String emailId);

    ResponseUtil addProfilePath(Long id, String token, String profilePath);

    Boolean validate(String token);
}
