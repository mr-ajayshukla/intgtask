package com.uos.intgtask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uos.intgtask.dto.CustOrderDto;
import com.uos.intgtask.repository.CustomerRepository;
import com.uos.intgtask.repository.entity.Customer;
import com.uos.intgtask.repository.entity.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustOrderService {

	private final CustomerRepository customerRepo;

	/**
	 * Get all the customer and their orders sorted by customerId (asc) and then by Order date (desc)
	 * @return orderList
	 */
	public List<CustOrderDto> getAllCustomerAndOrders() throws Exception{

		List<Customer> customersList = customerRepo.findByStatusOrderByIdAsc("active");
		if (customersList.isEmpty()) {
			throw new Exception("No records were found!");
		}
		return sortCustOrdersList(customersList);
	}
	
	/**
	 * Get all the orders of a particular customer by its Id and sorted by customerId (asc) & then by Order date (desc)
	 * @return orderList
	 */
	public List<CustOrderDto> getCustomerAndOrderByCustId(Long customerId) throws Exception {

		List<Customer> customersList = customerRepo.findByStatusAndIdOrderByIdAsc("active", customerId);
		if (customersList.isEmpty()) {
			throw new Exception("No records were found for the customer " + customerId + " !");
		}
		return sortCustOrdersList(customersList);
		
	}

	private List<CustOrderDto> sortCustOrdersList(List<Customer> customersList) {
		
		List<CustOrderDto> allCustomerAndOrders = new ArrayList<>();
		
		customersList.forEach(c -> {
			
			List<Order> orders = c.getOrders();
			orders.sort( (o1, o2) -> 
				o2.getDate().compareTo(o1.getDate())
			);
			
			orders.forEach(o -> {
				CustOrderDto order = CustOrderDto.builder()
						.customerId(c.getId())
						.firstName(c.getFirstname())
						.surName(c.getSurname())
						.email(c.getEmail())
						.region(c.getRegion())
						.address(c.getAddress())
						.zipCode(c.getZip_code())
						.orderAmount(o.getAmount())
						.orderDate(o.getDate())
						.orderId(o.getOrder_id())
						.build();
				
				allCustomerAndOrders.add(order);
			});
			
		});
		return allCustomerAndOrders;
	}

}
