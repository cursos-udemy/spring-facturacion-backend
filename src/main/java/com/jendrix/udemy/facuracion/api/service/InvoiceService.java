package com.jendrix.udemy.facuracion.api.service;

import com.jendrix.udemy.facuracion.api.model.entity.Invoice;

public interface InvoiceService {

	public Invoice findById(Long id);
	
	public Iterable<Invoice> findByCustomerId(Long customerId);

	public Invoice save (Invoice invoice);
	
	public void delete (Long id);

}
