package com.tekravio.payFlow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tekravio.payFlow.entity.Merchant;
import com.tekravio.payFlow.service.MerchantService;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public Merchant createMerchant(
            @RequestBody Merchant merchant) {

        return merchantService.createMerchant(merchant);
    }

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public Merchant getMerchantById(
            @PathVariable Long id) {

        return merchantService.getMerchantById(id);
    }
}