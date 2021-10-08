package com.springboot.changecurrency.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Request {
	
	@NotNull(message = "El monto es requerido")
	@Min(1)
	@Digits(integer = 5 ,fraction=2,message ="Tipo de cambio no válido")
	private Float amount;
	
	@NotNull(message = "La moneda origen es requerida")
	private String sourceCurrency;
	
	@NotNull(message = "La moneda destino es requerida")
	private String destinationCurrency;

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
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
	
	
	

}
