package com.jendrix.udemy.facuracion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jendrix.udemy.facuracion.api.model.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

	public Iterable<Invoice>findByCustomerId(Long customerId);
	
}
