package com.jendrix.udemy.facuracion.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;
import com.jendrix.udemy.facuracion.api.model.entity.Region;
import com.jendrix.udemy.facuracion.api.repository.CustomerRepository;
import com.jendrix.udemy.facuracion.api.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Customer> findAll(Pageable pageable) {
		return this.customerRepository.findAll(pageable);
	}	
	
	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id) {
		return this.customerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Customer save(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = false)	
	public void delete(Long id) {
		this.customerRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegions() {
		return this.customerRepository.findAllRegions();
	}
}
