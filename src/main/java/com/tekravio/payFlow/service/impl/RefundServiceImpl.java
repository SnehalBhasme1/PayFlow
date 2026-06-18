package com.tekravio.payFlow.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.tekravio.payFlow.dto.request.RefundRequest;
import com.tekravio.payFlow.entity.Payment;
import com.tekravio.payFlow.entity.Refund;
import com.tekravio.payFlow.entity.Wallet;
import com.tekravio.payFlow.enums.RefundStatus;
import com.tekravio.payFlow.enums.RefundType;
import com.tekravio.payFlow.repository.PaymentRepository;
import com.tekravio.payFlow.repository.RefundRepository;
import com.tekravio.payFlow.repository.WalletRepository;
import com.tekravio.payFlow.service.RefundService;

@Service
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final PaymentRepository paymentRepository;
    private final WalletRepository walletRepository;

    public RefundServiceImpl(
            RefundRepository refundRepository,
            PaymentRepository paymentRepository,
            WalletRepository walletRepository) {

        this.refundRepository = refundRepository;
        this.paymentRepository = paymentRepository;
        this.walletRepository = walletRepository;
    }

	@Override

	public String requestRefund(RefundRequest request) {

	    Payment payment = paymentRepository.findById(
	            request.getPaymentId())
	            .orElseThrow(() ->
	                    new RuntimeException("Payment not found"));

	    if (!payment.getStatus().equalsIgnoreCase("SUCCESS")) {
	        throw new RuntimeException(
	                "Only successful payments can be refunded");
	    }

	    Refund refund = new Refund();

	    refund.setPayment(payment);
	    refund.setRefundAmount(request.getAmount());
	    refund.setRefundType(RefundType.PARTIAL);
	    refund.setStatus(RefundStatus.PENDING);
	    refund.setReason(request.getReason());
	    refund.setCreatedAt(LocalDateTime.now());

	    refundRepository.save(refund);

	    return "Refund request submitted successfully";
	}

	@Override
	public String approveRefund(Long refundId) {

	    Refund refund = refundRepository.findById(refundId)
	            .orElseThrow(() ->
	                    new RuntimeException("Refund not found"));

	    Payment payment = refund.getPayment();

	    Wallet wallet = walletRepository
	            .findByUserId(payment.getUserId())
	            .orElseThrow(() ->
	                    new RuntimeException("Wallet not found"));

	    wallet.setBalance(
	            wallet.getBalance()
	            .add(refund.getRefundAmount()));

	    walletRepository.save(wallet);

	    refund.setStatus(RefundStatus.APPROVED);

	    refundRepository.save(refund);

	    return "Refund approved successfully";
	}

	@Override
	public String rejectRefund(Long refundId) {

	    Refund refund = refundRepository.findById(refundId)
	            .orElseThrow(() ->
	                    new RuntimeException("Refund not found"));

	    refund.setStatus(RefundStatus.REJECTED);

	    refundRepository.save(refund);

	    return "Refund rejected successfully";
	}
}