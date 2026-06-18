package com.tekravio.payFlow.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tekravio.payFlow.entity.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}