package com.example.youcodeRecruitment.Controller.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @GetMapping("get")
    public ResponseEntity<String> sayHello()
    {
        System.out.println("inside");
        return ResponseEntity.ok().body("Hello World !");
    }
}