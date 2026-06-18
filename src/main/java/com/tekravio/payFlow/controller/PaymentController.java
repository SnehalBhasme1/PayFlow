package com.tekravio.payFlow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tekravio.payFlow.dto.request.PaymentRequest;
import com.tekravio.payFlow.service.PaymentService;
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> makePayment(
            @RequestBody PaymentRequest request,
            @RequestHeader("X-Idempotency-Key") String idempotencyKey) {

        return ResponseEntity.ok(
                paymentService.makePayment(
                        request,
                        idempotencyKey));
    }
}