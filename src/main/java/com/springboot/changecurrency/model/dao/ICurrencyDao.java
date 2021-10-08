package com.springboot.changecurrency.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.changecurrency.model.entity.Currency;



public interface ICurrencyDao extends CrudRepository<Currency, Long>{
	
	public Currency getCurrencyByValueAndBaseCurrency(String value,String baseCurrency);

}
