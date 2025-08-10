package com.dkowalczyk.psinder_app.controller;

import com.dkowalczyk.psinder_app.dto.AuthResponse;
import com.dkowalczyk.psinder_app.dto.CreateUserRequest;
import com.dkowalczyk.psinder_app.dto.LoginRequest;
import com.dkowalczyk.psinder_app.dto.UserDto;
import com.dkowalczyk.psinder_app.service.AuthService;
import com.dkowalczyk.psinder_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody CreateUserRequest request) {
        UserDto createdUser = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
