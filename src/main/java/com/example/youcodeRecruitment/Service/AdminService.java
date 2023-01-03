package com.example.youcodeRecruitment.Service;


import com.example.youcodeRecruitment.Entity.AdminEntity;
import com.example.youcodeRecruitment.Repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    public AdminEntity find_user_by_email(String email) {
        return adminRepository.findByEmail(email);
    }
}
