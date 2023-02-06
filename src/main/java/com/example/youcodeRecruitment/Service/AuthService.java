package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Admin;
import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Repository.AdminRepository;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Repository.HRRepository;
import com.example.youcodeRecruitment.Request.AuthRequest;
import com.example.youcodeRecruitment.Request.RegisterRequest;
import com.example.youcodeRecruitment.Security.JwtUtils;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class AuthService {
    private final JwtUtils jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final CandidateRepository candidateRepository;
    private final HRRepository hrRepository;
    private final IMapperDto<RegisterRequest, Admin> mapperDtoAdmin;
    private final IMapperDto<RegisterRequest, Candidate> mapperDtoCandidate;
    private final IMapperDto<RegisterRequest, HR> mapperDtoHR;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<HashMap<String, String>> login(AuthRequest authRequest) {
        Admin admin = adminRepository.findByEmail(authRequest.getEmail()).orElse(null);
        if (admin != null) {
            HashMap<String, String> responseToken = new HashMap<>();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            responseToken.put("accessToken", jwtUtil.generateToken(admin, admin.getId_user()));
            return ResponseEntity.ok(responseToken);
        }

        HR hr = hrRepository.findByEmail(authRequest.getEmail()).orElse(null);
        if (hr != null) {
            HashMap<String, String> responseToken = new HashMap<>();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            responseToken.put("accessToken", jwtUtil.generateToken(hr, hr.getId_user()));
            return ResponseEntity.ok(responseToken);
        }

        Candidate candidate = candidateRepository.findByEmail(authRequest.getEmail()).orElse(null);
        if (candidate != null) {
            HashMap<String, String> responseToken = new HashMap<>();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            responseToken.put("accessToken", jwtUtil.generateToken(candidate, candidate.getId_user()));
            return ResponseEntity.ok(responseToken);
        }
        throw new UsernameNotFoundException("User not found");
    }

    public void register(RegisterRequest registerRequest) throws Exception {
        boolean checkIfUserExists = adminRepository.findByEmail(registerRequest.getEmail()).isPresent() ||
                hrRepository.findByEmail(registerRequest.getEmail()).isPresent() ||
                candidateRepository.findByEmail(registerRequest.getEmail()).isPresent();
        if (checkIfUserExists) {
            throw new Exception("User already exists");
        }
        switch (registerRequest.getRole()) {
            case "ROLE_ADMIN" -> {
                if (adminRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
                    throw new Exception("User already exists");
                }
                Admin admin = mapperDtoAdmin.convertToEntity(registerRequest, Admin.class);
                if (admin != null) {
                    admin.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                    adminRepository.save(admin);
                } else {
                    throw new Exception("User not found");
                }
            }
            case "ROLE_HR" -> {
                if (hrRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
                    throw new Exception("User already exists");
                }
                HR hr = mapperDtoHR.convertToEntity(registerRequest, HR.class);
                if (hr != null) {
                    hr.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                    hrRepository.save(hr);
                } else {
                    throw new Exception("User not found");
                }
            }
            case "ROLE_CANDIDATE" -> {
                if (candidateRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
                    throw new Exception("User already exists");
                }
                Candidate candidate = mapperDtoCandidate.convertToEntity(registerRequest, Candidate.class);
                if (candidate != null) {
                    candidate.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                    candidateRepository.save(candidate);
                } else {
                    throw new Exception("User not found");
                }
            }
            default -> throw new Exception("User not found");
        }
    }

    public Candidate getAuthenticatedCandidate() {
        return candidateRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    public HR getAuthenticatedHR() {
        return hrRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

    public Admin getAuthenticatedAdmin() {
        return adminRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }


}