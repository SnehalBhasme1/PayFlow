package com.tekravio.payFlow.service;

import java.util.List;

import com.tekravio.payFlow.entity.Merchant;

public interface MerchantService {

    Merchant createMerchant(Merchant merchant);

    List<Merchant> getAllMerchants();

    Merchant getMerchantById(Long id);
}