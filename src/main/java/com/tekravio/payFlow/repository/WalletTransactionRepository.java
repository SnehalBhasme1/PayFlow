package com.tekravio.payFlow.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.tekravio.payFlow.entity.WalletTransaction;


public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

}