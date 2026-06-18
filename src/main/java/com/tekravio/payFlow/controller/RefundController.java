package com.tekravio.payFlow.controller;
import org.springframework.web.bind.annotation.*;

import com.tekravio.payFlow.dto.request.RefundRequest;
import com.tekravio.payFlow.service.RefundService;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/request")
    public String requestRefund(
            @RequestBody RefundRequest request) {

        return refundService.requestRefund(request);
    }

    @PutMapping("/{id}/approve")
    public String approveRefund(
            @PathVariable Long id) {

        return refundService.approveRefund(id);
    }

    @PutMapping("/{id}/reject")
    public String rejectRefund(
            @PathVariable Long id) {

        return refundService.rejectRefund(id);
    }
}