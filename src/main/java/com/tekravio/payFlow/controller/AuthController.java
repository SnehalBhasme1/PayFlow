package com.tekravio.payFlow.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.tekravio.payFlow.dto.request.LoginRequest;
import com.tekravio.payFlow.entity.User;
import com.tekravio.payFlow.repository.UserRepository;
import com.tekravio.payFlow.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.getPassword()
                .equals(request.getPassword())) {

            throw new RuntimeException(
                    "Invalid credentials");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        Map<String, String> response =
                new HashMap<>();

        response.put("token", token);

        return response;
    }
}