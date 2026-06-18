package com.tekravio.payFlow.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekravio.payFlow.dto.request.RegisterRequest;
import com.tekravio.payFlow.entity.User;
import com.tekravio.payFlow.entity.Wallet;
import com.tekravio.payFlow.repository.UserRepository;
import com.tekravio.payFlow.repository.WalletRepository;
import com.tekravio.payFlow.service.UserService;

import lombok.RequiredArgsConstructor;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();

        wallet.setUser(savedUser);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCurrency("INR");
        wallet.setStatus("ACTIVE");

        walletRepository.save(wallet);

        return "User Registered Successfully";
    }
}