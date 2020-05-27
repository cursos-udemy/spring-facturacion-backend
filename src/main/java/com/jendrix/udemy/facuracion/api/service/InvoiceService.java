package com.jendrix.udemy.facuracion.api.service;

import com.jendrix.udemy.facuracion.api.model.entity.Invoice;

public interface InvoiceService {

	public Iterable<Invoice> findByCustomerId(Long customerId);

}
