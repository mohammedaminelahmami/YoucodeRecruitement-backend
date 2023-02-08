package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Service.AuthService;
import com.example.youcodeRecruitment.dto.AdminDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminController {

    private final AuthService authService;

    @GetMapping("/admin/profile")
    public AdminDTO getProfile() {
        return authService.getProfileAdmin();
    }


}
