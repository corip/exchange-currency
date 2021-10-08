package com.springboot.changecurrency.model.services;

import java.util.List;

import com.springboot.changecurrency.model.dto.CurrencyDto;
import com.springboot.changecurrency.model.dto.Request;
import com.springboot.changecurrency.model.dto.Response;


public interface ICurrencyService {
	
	public List<CurrencyDto> getCurrencies();
	public Response exchangeCurrency(Request request);
	public CurrencyDto updateCurrency(CurrencyDto currency);

}
