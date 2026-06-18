package com.tekravio.payFlow.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekravio.payFlow.dto.request.PaymentRequest;
import com.tekravio.payFlow.entity.Payment;
import com.tekravio.payFlow.entity.User;
import com.tekravio.payFlow.entity.Wallet;
import com.tekravio.payFlow.entity.WalletTransaction;
import com.tekravio.payFlow.repository.PaymentRepository;
import com.tekravio.payFlow.repository.UserRepository;
import com.tekravio.payFlow.repository.WalletRepository;
import com.tekravio.payFlow.repository.WalletTransactionRepository;
import com.tekravio.payFlow.service.PaymentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private WalletRepository walletRepository;

	    @Autowired
	    private WalletTransactionRepository transactionRepository;

    @Override
    public String makePayment(
            PaymentRequest request,
            String idempotencyKey) {

        if(paymentRepository
                .findByIdempotencyKey(idempotencyKey)
                .isPresent()) {

            return "Duplicate Payment Request";
        }

        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow();

        Wallet wallet = walletRepository
                .findByUser(user)
                .orElseThrow();

        if(wallet.getBalance()
                .compareTo(request.getAmount()) < 0) {

            return "Insufficient Balance";
        }

        wallet.setBalance(
                wallet.getBalance()
                        .subtract(request.getAmount()));

        walletRepository.save(wallet);

        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setMerchantId(request.getMerchantId());
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setStatus("SUCCESS");
        payment.setIdempotencyKey(idempotencyKey);
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        WalletTransaction transaction = new WalletTransaction();
        
transaction.setWalletId(wallet.getId());
transaction.setType("DEBIT");
transaction.setAmount(request.getAmount());
transaction.setReferenceId(request.getOrderId());
transaction.setCreatedAt(LocalDateTime.now());


        transactionRepository.save(transaction);

        return "Payment Successful";
    }
}