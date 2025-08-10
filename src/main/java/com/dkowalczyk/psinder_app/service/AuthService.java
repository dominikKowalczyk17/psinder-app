package com.dkowalczyk.psinder_app.service;

import com.dkowalczyk.psinder_app.dto.AuthResponse;
import com.dkowalczyk.psinder_app.dto.LoginRequest;
import com.dkowalczyk.psinder_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        
        var user = userRepository.findByName(request.getName())
                .orElseThrow();
        
        var jwtToken = jwtService.generateToken(
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getName())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build()
        );
        
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
