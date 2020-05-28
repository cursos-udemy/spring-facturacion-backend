package com.jendrix.udemy.facuracion.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facuracion.api.model.entity.Invoice;
import com.jendrix.udemy.facuracion.api.repository.InvoiceRepository;
import com.jendrix.udemy.facuracion.api.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	@Transactional(readOnly = true)	
	public Invoice findById(Long id) {
		return this.invoiceRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Invoice> findByCustomerId(Long customerId) {
		return this.invoiceRepository.findByCustomerId(customerId);
	}

	@Override
	@Transactional	
	public Invoice save(Invoice invoice) {
		invoice.setCreatedat(new Date());
		return this.invoiceRepository.save(invoice);
	}

	@Override
	@Transactional	
	public void delete(Long id) {
		this.invoiceRepository.deleteById(id);
	}
}
