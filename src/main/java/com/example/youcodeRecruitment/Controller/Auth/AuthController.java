package com.example.youcodeRecruitment.Controller.Auth;

import com.example.youcodeRecruitment.Entity.AdminEntity;
import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Entity.HREntity;
import com.example.youcodeRecruitment.Repository.UserDao;
import com.example.youcodeRecruitment.Request.AuthenticationRequest;
import com.example.youcodeRecruitment.Security.JwtUtils;
import com.example.youcodeRecruitment.Service.AdminService;
import com.example.youcodeRecruitment.Service.AuthService;
import com.example.youcodeRecruitment.Service.CandidateService;
import com.example.youcodeRecruitment.Service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private  UserDao userDao;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private HRService hrService;
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){


        // ---- for dont repate your selfe -------------------------------------------------------
        // UsernamePasswordAuthenticationToken user =new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        //this.authenticationManager.authenticate(user);
        //Object authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("authentication :"+"-".repeat(100));
        //System.out.println(authentication);

        authService.login(authenticationRequest);
        System.out.println("back to auth controller");
        CandidateEntity candidate = candidateService.find_user_by_email(authenticationRequest.getEmail());

        if (candidate != null) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    candidate.getEmail(),
                    candidate.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("candidate")));

            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }
        HREntity hr = hrService.find_user_by_email(authenticationRequest.getEmail());

        if (hr != null) {

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    hr.getEmail(),
                    hr.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("HR")));

            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }

        AdminEntity admin = adminService.find_user_by_email(authenticationRequest.getEmail());

        if (admin != null) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    admin.getEmail(),
                    admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("HR")));

            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }
        return ResponseEntity.status(400).body("Some error : token has not generated");

    }
}