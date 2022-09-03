package com.example.lmsadminservice.service;

import com.example.lmsadminservice.dto.AdminDTO;
import com.example.lmsadminservice.exception.AdminNotFoundException;
import com.example.lmsadminservice.model.AdminModel;
import com.example.lmsadminservice.repository.AdminRepository;
import com.example.lmsadminservice.util.Response;
import com.example.lmsadminservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;

    @Override
    public AdminModel addAdmin(AdminDTO adminDTO) {
        AdminModel adminModel = new AdminModel(adminDTO);
        adminModel.setCreatorStamp(LocalDateTime.now());
        adminRepository.save(adminModel);
        String body = "Admin is added successfully with adminId " + adminModel.getId();
        String subject = "Admin registration successful";
        mailService.send(adminModel.getEmailId(), subject, body);
        return adminModel;
    }

    @Override
    public AdminModel updateAdmin(Long id, AdminDTO adminDTO, String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = adminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            Optional<AdminModel> isAdminPresent = adminRepository.findById(id);
            if (isAdminPresent.isPresent()) {
                isAdminPresent.get().setFirstName(adminDTO.getFirstName());
                isAdminPresent.get().setLastName(adminDTO.getLastName());
                isAdminPresent.get().setMobileNumber(adminDTO.getMobileNumber());
                isAdminPresent.get().setEmailId(adminDTO.getEmailId());
                isAdminPresent.get().setProfilePath(adminDTO.getProfilePath());
                isAdminPresent.get().setPassword(adminDTO.getPassword());
                isAdminPresent.get().setStatus(adminDTO.getStatus());
                isAdminPresent.get().setUpdatedStamp(LocalDateTime.now());
                adminRepository.save(isAdminPresent.get());
                String body = "Admin is added successfully with adminId " + isAdminPresent.get().getId();
                String subject = "Admin registration successful";
                mailService.send(isAdminPresent.get().getEmailId(), subject, body);
                return isAdminPresent.get();
            } else {
                throw new AdminNotFoundException(400, "Admin not found");
            }
        }
        throw new AdminNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<AdminModel> getAdmins(String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = adminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            List<AdminModel> isAdminPresent = adminRepository.findAll();
            if (isAdminPresent.size() > 0) {
                return isAdminPresent;
            } else {
                throw new AdminNotFoundException(400, "No admin is present");
            }
        }
        throw new AdminNotFoundException(400, "Token is wrong");
    }

    @Override
    public AdminModel deleteAdmin(Long id, String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = adminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            Optional<AdminModel> isAdminPresent = adminRepository.findById(id);
            if (isAdminPresent.isPresent()) {
                adminRepository.delete(isAdminPresent.get());
                return isAdminPresent.get();
            } else {
                throw new AdminNotFoundException(400, "Admin not found");
            }
        }
        throw new AdminNotFoundException(400, "Token is wrong");
    }

    @Override
    public AdminModel getAdmin(Long id, String token) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = adminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            Optional<AdminModel> isAdminPresent = adminRepository.findById(id);
            if (isAdminPresent.isPresent()) {
                return isAdminPresent.get();
            } else {
                throw new AdminNotFoundException(400, "Admin not found");
            }
        }
        throw new AdminNotFoundException(400, "Token is wrong");

    }

    @Override
    public Response login(String emailId, String password) {
        Optional<AdminModel> isEmailPresent = adminRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new Response(200, "LoginSuccessful", token);
            }
            throw new AdminNotFoundException(400, "Password wrong");
        }
        throw new AdminNotFoundException(400, "No admin present");
    }

    @Override
    public AdminModel changePassword(String token, String password) {
        Long adminId = tokenUtil.decodeToken(token);
        Optional<AdminModel> isAdmin = adminRepository.findById(adminId);
        if (isAdmin.isPresent()) {
            isAdmin.get().setPassword(password);
            adminRepository.save(isAdmin.get());
            return isAdmin.get();
        }
        throw new AdminNotFoundException(400, "Token is wrong");

    }

    @Override
    public AdminModel resetPassword(String emailId) {
        Optional<AdminModel> isEmailIdPresent = adminRepository.findByEmailId(emailId);
        if (isEmailIdPresent.isPresent()) {
            String token = tokenUtil.createToken(isEmailIdPresent.get().getId());
            String url = "http://localhost:8080/admin/changepassword";
            String subject = "Reset Password";
            String body = "Reset password click on this link \n" + url + "\n use this token to reset" + token;
            mailService.send(isEmailIdPresent.get().getEmailId(), body, subject);
            return isEmailIdPresent.get();
        }
        throw new AdminNotFoundException(400, "Token is wrong");

    }
}
