package com.uos.intgtask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uos.intgtask.repository.entity.Customer;

/**
 * Repository interface for Customer entity
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public List<Customer> findByStatusOrderByIdAsc(String status);
	
	public List<Customer> findByStatusAndIdOrderByIdAsc(String status, Long Id);
}
