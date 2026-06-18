package com.tekravio.payFlow.service;

import com.tekravio.payFlow.dto.request.LoginRequest;
import com.tekravio.payFlow.dto.request.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}