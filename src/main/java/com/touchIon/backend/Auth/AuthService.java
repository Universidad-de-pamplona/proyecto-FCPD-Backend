package com.touchIon.backend.Auth;

import com.touchIon.backend.Auth.DTO.LoginRequest;
import com.touchIon.backend.model.User;
import com.touchIon.backend.jwt.JwtService;
import com.touchIon.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.logging.Logger;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = Logger.getLogger(getClass().getName());

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse  createToken(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String email = request.getEmail();
        User user = userService.findByEmail(email);
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }

    public Boolean checkCredentials( LoginRequest request) {
        String email = request.getEmail();
        User user = userService.findByEmail(email);
        boolean isPasswordCorrect =  passwordEncoder.matches(request.getPassword(), user.getPassword());
        boolean isEmailCorrect = email.equals(user.getEmail());
        return isPasswordCorrect && isEmailCorrect;
    }

    public User register( User request) {
        logger.info("Creating user: " + request.getUsername());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        logger.info("Password encoded successfully: "+encodedPassword );
        User createdUser = userService.save(request);
        logger.info("User created successfully, user uuid: " + createdUser.getId());
        return createdUser;
    }

}