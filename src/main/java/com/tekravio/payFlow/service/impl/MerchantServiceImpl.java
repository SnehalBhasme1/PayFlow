package com.tekravio.payFlow.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tekravio.payFlow.entity.Merchant;
import com.tekravio.payFlow.repository.MerchantRepository;
import com.tekravio.payFlow.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getMerchantById(Long id) {
        return merchantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Merchant not found"));
    }
}