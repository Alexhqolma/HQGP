package com.hqguestposting.controller;

import com.hqguestposting.dto.request.UserRequestDto;
import com.hqguestposting.dto.response.UserResponseDto;
import com.hqguestposting.security.AuthenticationResponse;
import com.hqguestposting.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    /*@Value("${recaptcha.secret}")
    private String reCaptchaSecret;*/

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserResponseDto userResponseDto) {
        return ResponseEntity.ok((authenticationService.login(userResponseDto)));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> registration(
            @RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok((authenticationService.registration(userRequestDto)));
    }
}
