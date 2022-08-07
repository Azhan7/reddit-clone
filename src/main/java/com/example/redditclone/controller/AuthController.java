package com.example.redditclone.controller;
import com.example.redditclone.dto.RegisterRequest;
import com.example.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        log.info("In here");
        authService.signUp(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }
    @GetMapping("/test")
    public String test() {
        return "In here";
    }
}