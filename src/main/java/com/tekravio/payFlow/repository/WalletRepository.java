package com.tekravio.payFlow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekravio.payFlow.entity.User;
import com.tekravio.payFlow.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(Long userId);
}