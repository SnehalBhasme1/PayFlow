package com.tekravio.payFlow.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekravio.payFlow.dto.request.TopUpRequest;
import com.tekravio.payFlow.entity.User;
import com.tekravio.payFlow.entity.Wallet;
import com.tekravio.payFlow.entity.WalletTransaction;
import com.tekravio.payFlow.repository.UserRepository;
import com.tekravio.payFlow.repository.WalletRepository;
import com.tekravio.payFlow.repository.WalletTransactionRepository;
import com.tekravio.payFlow.service.WalletService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service

@Transactional
public class WalletServiceImpl implements WalletService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private WalletTransactionRepository transactionRepository;

    @Override
    public String topUp(TopUpRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow();

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow();

        wallet.setBalance(
                wallet.getBalance().add(request.getAmount()));

        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction();

        transaction.setWalletId(wallet.getId());
        transaction.setType("CREDIT");
        transaction.setAmount(request.getAmount());
        transaction.setReferenceId(request.getTransactionRef());
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Wallet topped up successfully";
    }
}