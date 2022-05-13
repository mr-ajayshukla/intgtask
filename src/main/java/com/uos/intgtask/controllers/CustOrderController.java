package com.uos.intgtask.controllers;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uos.intgtask.dto.CustOrderDto;
import com.uos.intgtask.service.CustOrderService;

import lombok.RequiredArgsConstructor;

@RestController("CustOrderController")
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustOrderController {
	
	private final CustOrderService custOrderService;

	@GetMapping("/customer-order")
	public ResponseEntity<List<CustOrderDto>> getAllCustomerAndOrders() throws Exception {

		List<CustOrderDto> customerAndOrdersList = custOrderService.getAllCustomerAndOrders();
		return new ResponseEntity<>(customerAndOrdersList, HttpStatus.OK);

	}
	
	@GetMapping("/customer-order/{customer-id}")
	public ResponseEntity<List<CustOrderDto>> getCustomerAndOrdersById(@PathVariable(name = "customer-id") @NotBlank Long customerId) throws Exception {

		List<CustOrderDto> customerAndOrdersList = custOrderService.getCustomerAndOrderByCustId(customerId);
		return new ResponseEntity<>(customerAndOrdersList, HttpStatus.OK);

	}

}
