package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.AuthRequest;
import com.example.youcodeRecruitment.Request.RegisterRequest;
import com.example.youcodeRecruitment.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

    @RequestMapping("/api/v1/authentication")
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HashMap<String, String>> userAuth(@RequestBody @Valid AuthRequest authRequest)
    {
        return authService.login(authRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        authService.register(registerRequest);
    }
}