package com.jendrix.udemy.facuracion.api.service;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;

public interface CustomerService {

	public Iterable<Customer> findAll();
	
	public Customer findById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Long id);
}
