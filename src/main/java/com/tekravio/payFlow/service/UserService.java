package com.tekravio.payFlow.service;

import com.tekravio.payFlow.dto.request.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);

}