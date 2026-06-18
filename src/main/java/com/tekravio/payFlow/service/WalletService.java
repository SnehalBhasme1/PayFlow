package com.tekravio.payFlow.service;

import com.tekravio.payFlow.dto.request.TopUpRequest;

public interface WalletService {

    String topUp(TopUpRequest request);

}