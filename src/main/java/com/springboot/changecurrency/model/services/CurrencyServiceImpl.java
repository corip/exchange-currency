package com.springboot.changecurrency.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.springboot.changecurrency.model.dao.ICurrencyDao;
import com.springboot.changecurrency.model.dto.CurrencyDto;
import com.springboot.changecurrency.model.dto.Request;
import com.springboot.changecurrency.model.dto.Response;
import com.springboot.changecurrency.model.entity.Currency;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

	@Autowired
	private ICurrencyDao currencyDao;

	@Override
	public List<CurrencyDto> getCurrencies() {
		// TODO Auto-generated method stub
		List<Currency> lsCurrencies ;
		List<CurrencyDto> lsCurrenciesDto = new ArrayList();
		CurrencyDto currencyDto;
		
		
		lsCurrencies = (List<Currency>) currencyDao.findAll();
		
		
		for(Currency currency: lsCurrencies) {
			currencyDto = new CurrencyDto();
			BeanUtils.copyProperties(currency, currencyDto);
			lsCurrenciesDto.add(currencyDto);
		}
		
		
		
		return lsCurrenciesDto;
	}

	@Override
	public Response exchangeCurrency(Request request){
		// TODO Auto-generated method stub
		Response response = new Response();
		Currency currency;

		try {
			currency = currencyDao.getCurrencyByValueAndBaseCurrency(request.getDestinationCurrency(),
					request.getSourceCurrency());

			if (currency != null) {
				response.setExchangeAmount(request.getAmount() * currency.getExchangeRate());
				response.setExchangeRate(currency.getExchangeRate());

				response.setAmount(request.getAmount());
				response.setDestinationCurrency(request.getDestinationCurrency());
				response.setSourceCurrency(request.getSourceCurrency());
			}
		} catch (DataAccessException  e) {
			
		}

		return response;

	}

	@Override
	public CurrencyDto updateCurrency(CurrencyDto currencyDto) {
		// TODO Auto-generated method stub
		Currency currency;

		try {
			currency = currencyDao.getCurrencyByValueAndBaseCurrency(currencyDto.getValue(),
					currencyDto.getBaseCurrency());

			if (currency != null) {
				currency.setExchangeRate(currencyDto.getExchangeRate());

				currency = currencyDao.save(currency);
				BeanUtils.copyProperties(currency, currencyDto);

			}else {
				currencyDto = null;  
			}
		}

		catch (DataAccessException e) {

		}
		return currencyDto;
	}

}
