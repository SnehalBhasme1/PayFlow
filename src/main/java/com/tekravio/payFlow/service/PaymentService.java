package com.tekravio.payFlow.service;

import com.tekravio.payFlow.dto.request.PaymentRequest;

public interface PaymentService {

    String makePayment(
            PaymentRequest request,
            String idempotencyKey);
}
