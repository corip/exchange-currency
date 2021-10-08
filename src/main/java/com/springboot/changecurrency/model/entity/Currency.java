package com.springboot.changecurrency.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currencies")
public class Currency implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String value;
	
	private String name;
	
	private String description;
	
	@Column(name="base_currency")
	private String baseCurrency;
	
	@Column(name="exchange_rate")
	private Float exchangeRate;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



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



	



	public Float getExchangeRate() {
		return exchangeRate;
	}



	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

	public String getBaseCurrency() {
		return baseCurrency;
	}



	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	

}
