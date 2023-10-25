package com.vermeg.dashboard.services;

import com.vermeg.dashboard.config.JwtConfig;
import com.vermeg.dashboard.entities.Users;
import com.vermeg.dashboard.repositories.UserRepository;
import com.vermeg.dashboard.requests.LoginRequest;
import com.vermeg.dashboard.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    public LoginResponse authenticate(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String jwtToken = jwtConfig.generateToken(auth);
        Users user = userRepository.findOneByEmail(request.getEmail()).orElse(null);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .user(user)
                .build();
    }

}
