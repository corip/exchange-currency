package com.springboot.changecurrency.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class CurrencyDto {
	
	
	@NotNull(message = "La moneda origen es requerida")
	private String value;
	
	private String name;
	
	private String description;
	
	@NotNull(message = "La moneda base es requerida")
	private String baseCurrency;
	
	@NotNull(message = "Tipo de cambio es requerido")
	@Digits(integer = 5 ,fraction=2, message ="Tipo de cambio no válido")
	private Float exchangeRate;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	
}
