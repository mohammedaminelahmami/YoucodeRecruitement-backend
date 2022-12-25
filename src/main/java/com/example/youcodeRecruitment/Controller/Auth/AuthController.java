package com.example.youcodeRecruitment.Controller.Auth;

import com.example.youcodeRecruitment.Repository.UserDao;
import com.example.youcodeRecruitment.Request.AuthenticationRequest;
import com.example.youcodeRecruitment.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserDao userDao;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserDao userDao, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        final UserDetails user = userDao.findUserByEmail(authenticationRequest.getEmail());
        if(user != null)
        {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occured");
    }
}