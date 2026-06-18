package com.tekravio.payFlow.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekravio.payFlow.entity.Payment;
import com.tekravio.payFlow.entity.Refund;
import com.tekravio.payFlow.enums.RefundStatus;


public interface RefundRepository extends JpaRepository<Refund, Long> {

    Optional<Refund> findByPaymentAndStatus(
            Payment payment,
            RefundStatus status);

}