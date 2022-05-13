package com.uos.intgtask.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uos.intgtask.dto.CustOrderDto;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ETLScheduler {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;
	@Value("${customerAPIBaseUrl}")
	private String customerAPIBaseUrl;
	@Value("${postmanEchoAPIUrl}")
	private String postmanEchoAPIUrl;

	// Run the scheduler in every 2 minutes
	@Scheduled(cron = "0 0/2 * * * *")
	public void scheduleETLJob() {
		
		log.info("Started the scheduler!");
		List<CustOrderDto> allCustomerAndOrders = new ArrayList<>();
		
		// Step 1 - Call the GetAllCustomerAndOrder API
		allCustomerAndOrders = getCustOrderData();
		
		// Step 2 - Transform the data by concatenating firstname and surname fields into a fullName field 
		allCustomerAndOrders.stream().forEach(c -> c.setFullName(c.getFirstName() + " " + c.getSurName()));
		
		// Step 3 - Call Postman Echo API
		callPostmanEchoAPI(allCustomerAndOrders);
		
	}

	private void callPostmanEchoAPI(List<CustOrderDto> allCustomerAndOrders) {
		UriComponents postmanEchoAPI = UriComponentsBuilder.fromHttpUrl(postmanEchoAPIUrl).buildAndExpand();
		HttpEntity<?> httpEntity = new HttpEntity<>(allCustomerAndOrders.toString());
		ResponseEntity<String> response = restTemplate.exchange(postmanEchoAPI.toUriString(), HttpMethod.POST, httpEntity, String.class);
		log.info("Postman Echo API Response code - {}", response.getStatusCode());
		
	}

	private List<CustOrderDto> getCustOrderData() {
		UriComponents getAllAPI = UriComponentsBuilder.fromHttpUrl(customerAPIBaseUrl).path("api/customer-order").buildAndExpand();
		ResponseEntity<String> response = restTemplate.exchange(getAllAPI.toUriString(), HttpMethod.GET, null, String.class);
		List<CustOrderDto> allCustomerAndOrders = new ArrayList<>();

		try {
			allCustomerAndOrders = mapper.readValue(response.getBody(), new TypeReference<List<CustOrderDto>>() {
			});
		}
		catch (JsonProcessingException e) {
			log.warn("Exception occured while parsing - ", e.getMessage());
		}
		
		return allCustomerAndOrders;
	}
}