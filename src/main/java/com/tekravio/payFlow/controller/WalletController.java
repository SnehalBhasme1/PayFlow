package com.tekravio.payFlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tekravio.payFlow.dto.request.TopUpRequest;
import com.tekravio.payFlow.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/topup")
    public ResponseEntity<String> topUp(
            @RequestBody TopUpRequest request) {

        return ResponseEntity.ok(
                walletService.topUp(request));
    }
}