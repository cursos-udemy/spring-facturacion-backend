package com.jendrix.udemy.facuracion.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;
import com.jendrix.udemy.facuracion.api.model.entity.Region;

public interface CustomerService {

	public Iterable<Customer> findAll();
	
	public Page<Customer> findAll(Pageable pageable);

	public Customer findById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Long id);
	
	public List<Region> findAllRegions();

}
