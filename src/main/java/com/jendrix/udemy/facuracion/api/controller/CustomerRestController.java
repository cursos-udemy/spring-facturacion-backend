package com.jendrix.udemy.facuracion.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		Map<String, Object> response = new HashMap<>();
		try {
			Customer customer = this.customerService.findById(id);
			if (customer == null) {
				response.put("message", "El cliente con ID ".concat(id.toString()).concat(" no existe"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (DataAccessException dae) {
			response.put("message", "No se puedo consultar el cliente. Servicio no disponible");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(fe -> String.format("El campo %s %s", fe.getField(), fe.getDefaultMessage()))
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			customer.setCreatedAt(new Date());
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
	public ResponseEntity<?> update(@Valid @RequestBody Customer customer, BindingResult result,  @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors()
						.stream()
						.map(fe -> String.format("El campo %s %s", fe.getField(), fe.getDefaultMessage()))
						.collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			Customer currentCustomer = this.customerService.findById(id);
			if (currentCustomer == null) {
				response.put("message", "El cliente con ID ".concat(id.toString()).concat(" no existe"));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			currentCustomer.setName(customer.getName());
			currentCustomer.setLastname(customer.getLastname());
			currentCustomer.setEmail(customer.getEmail());

			response.put("message", "Cliente actualizado con exito!");
			response.put("customer", this.customerService.save(currentCustomer));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "Error al actualizar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			this.customerService.delete(id);
			response.put("message", "Cliente eliminado con exito!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "Error al eliminar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
