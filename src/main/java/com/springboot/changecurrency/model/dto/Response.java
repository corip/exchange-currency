package com.springboot.changecurrency.model.dto;

import javax.validation.constraints.NotNull;

public class Response {

	public Float amount;

	public Float exchangeAmount;
	
	private String sourceCurrency;
	
	private String destinationCurrency;
	
	private Float exchangeRate;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Float exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	
	
	

}

