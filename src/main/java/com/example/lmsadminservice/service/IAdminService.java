package com.example.lmsadminservice.service;


import com.example.lmsadminservice.dto.AdminDTO;
import com.example.lmsadminservice.model.AdminModel;
import com.example.lmsadminservice.util.Response;


import java.util.List;

public interface IAdminService {
    AdminModel addAdmin(AdminDTO adminDTO);

    AdminModel updateAdmin(Long id, AdminDTO adminDTO, String token);

    List<AdminModel> getAdmins(String token);

    AdminModel deleteAdmin(Long id, String token);

    AdminModel getAdmin(Long id, String token);

    Response login(String emailId, String password);

    AdminModel changePassword(String token, String password);

    AdminModel resetPassword(String emailId);

}
