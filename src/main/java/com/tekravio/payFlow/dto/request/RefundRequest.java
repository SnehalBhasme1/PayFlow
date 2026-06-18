package com.tekravio.payFlow.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RefundRequest {

    public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private Long paymentId;

    private BigDecimal amount;

    private String reason;

	
}