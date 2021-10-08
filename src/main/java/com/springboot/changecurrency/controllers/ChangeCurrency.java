package com.springboot.changecurrency.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.changecurrency.model.dto.CurrencyDto;
import com.springboot.changecurrency.model.dto.Request;
import com.springboot.changecurrency.model.dto.Response;
import com.springboot.changecurrency.model.entity.Currency;
import com.springboot.changecurrency.model.services.ICurrencyService;


@RestController
@RequestMapping("/change-currency")
public class ChangeCurrency {
	
	@Autowired
	private ICurrencyService currencyService;
	
	@PostMapping
	public ResponseEntity<?> exchangeAmount(@Validated @RequestBody Request request, BindingResult result){
		Map<String,Object> responseEntity = new HashMap<String, Object>();
		Response response;
		
		if(result.hasErrors()){
			List<Object> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
					
			responseEntity.put("message", errors);
			return  new ResponseEntity<Map<String,Object>>(responseEntity,HttpStatus.BAD_REQUEST);
		}
		
		response = currencyService.exchangeCurrency(request);
			
		responseEntity.put("response", response);
		return  new ResponseEntity<Map<String,Object>>(responseEntity,HttpStatus.OK);
			

		
	}
	

	
	@PutMapping
	public ResponseEntity<?> updateCurrency(@Validated @RequestBody CurrencyDto currencyDto, BindingResult result){
		Map<String,Object> responseEntity = new HashMap<String, Object>();
		
		if(result.hasErrors()){
			List<Object> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getDefaultMessage())
					.collect(Collectors.toList());
					
			responseEntity.put("message", errors);
			return  new ResponseEntity<Map<String,Object>>(responseEntity,HttpStatus.BAD_REQUEST);
		}
		
		currencyDto = currencyService.updateCurrency(currencyDto);
		
		if(currencyDto != null) {
			responseEntity.put("currency", currencyDto);
			responseEntity.put("status", "ok");
		}else {
			responseEntity.put("status", "moneda no existe");
		}
		
		
		return  new ResponseEntity<Map<String,Object>>(responseEntity,HttpStatus.OK);
		
	}
	
	
	@GetMapping
	public ResponseEntity<?> getResponse(){
		Map<String,Object> responseEntity = new HashMap<String, Object>();
		List<CurrencyDto> currencies;
		
		
		currencies = currencyService.getCurrencies();
		responseEntity.put("currencies", currencies);
		return  new ResponseEntity<Map<String,Object>>(responseEntity,HttpStatus.OK);
	}

}
