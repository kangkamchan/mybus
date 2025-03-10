package net.mybus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationApiController {
    @PostMapping("/login")
    public ResponseEntity<?> login(){
        return null;
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return null;
    }
}
