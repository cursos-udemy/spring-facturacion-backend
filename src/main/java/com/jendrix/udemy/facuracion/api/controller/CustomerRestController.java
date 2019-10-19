package com.jendrix.udemy.facuracion.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;
import com.jendrix.udemy.facuracion.api.service.CustomerService;

@CrossOrigin(origins = { "${app.api.settings.cross-origin.urls}" })
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public Iterable<Customer> getCustomers() {
		return this.customerService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
		try {
			Customer customer = this.customerService.findById(id);
			Map<String, Object> response = new HashMap<>();
			if (customer == null) {
				response.put("message", "El cliente con ID ".concat(id.toString()).concat(" no existe"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (DataAccessException dae) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "No se puedo consultar el cliente. Servicio no disponible");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody Customer customer) {
		Map<String, Object> response = new HashMap<>();
		try {
			Customer newCustomer = this.customerService.save(customer);
			response.put("message", "Cliente creado con exito!");
			response.put("customer", newCustomer);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "Error al crear el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer update(@RequestBody Customer customer, @PathVariable Long id) {
		Customer currentCustomer = this.customerService.findById(id);
		currentCustomer.setName(customer.getName());
		currentCustomer.setLastname(customer.getLastname());
		currentCustomer.setEmail(customer.getEmail());
		return this.customerService.save(currentCustomer);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.customerService.delete(id);
	}
}
