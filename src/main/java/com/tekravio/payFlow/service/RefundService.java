package com.tekravio.payFlow.service;

import com.tekravio.payFlow.dto.request.RefundRequest;

public interface RefundService {

    String requestRefund(RefundRequest request);

    String approveRefund(Long refundId);

    String rejectRefund(Long refundId);
}