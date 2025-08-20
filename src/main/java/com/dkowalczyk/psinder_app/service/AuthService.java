package com.dkowalczyk.psinder_app.service;

import com.dkowalczyk.psinder_app.dto.AuthResponse;
import com.dkowalczyk.psinder_app.dto.LoginRequest;
import com.dkowalczyk.psinder_app.exceptions.InvalidRefreshTokenException;
import com.dkowalczyk.psinder_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getName(),
                            request.getPassword()
                    )
            );
            
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getName());
            
            var jwtToken = jwtService.generateToken(userDetails);
            var refreshToken = jwtService.generateRefreshToken(userDetails);
            
            return AuthResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    public AuthResponse refreshToken(String refreshToken) {
        try {
                String username = jwtService.extractUsername(refreshToken);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (!jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
                        throw new InvalidRefreshTokenException("Invalid refresh token for user: " + username);
                }

                String newAccessToken = jwtService.generateToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(userDetails);

                return AuthResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .expiresIn(86400)
                        .refreshExpiresIn(604800)
                        .build();
        } catch (Exception e) {
                throw new RuntimeException("Token refresh failed", e);
        }
    }
}
