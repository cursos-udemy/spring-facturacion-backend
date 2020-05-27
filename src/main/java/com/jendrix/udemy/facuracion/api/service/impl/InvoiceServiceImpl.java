package com.jendrix.udemy.facuracion.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jendrix.udemy.facuracion.api.model.entity.Invoice;
import com.jendrix.udemy.facuracion.api.repository.InvoiceRepository;
import com.jendrix.udemy.facuracion.api.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public Iterable<Invoice> findByCustomerId(Long customerId) {
		return this.invoiceRepository.findByCustomerId(customerId);
	}

}
