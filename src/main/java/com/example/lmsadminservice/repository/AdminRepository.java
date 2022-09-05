package com.example.lmsadminservice.repository;


import com.example.lmsadminservice.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Purpose: Creating repository for admin
 * @author: Annu Kumari
 * @Param:  To save in database
 * Version: 1.0
 */

public interface AdminRepository extends JpaRepository<AdminModel, Long> { Optional<AdminModel> findByEmailId(String emailId);
}
