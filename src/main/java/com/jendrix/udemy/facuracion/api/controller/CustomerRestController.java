package com.jendrix.udemy.facuracion.api.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jendrix.udemy.facuracion.api.model.entity.Customer;
import com.jendrix.udemy.facuracion.api.service.CustomerService;

@CrossOrigin(origins = { "${app.api.settings.cross-origin.urls}" })
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController {

	private final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public Iterable<Customer> getCustomers(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
		return this.customerService.findAll(PageRequest.of(page, limit));
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
	public ResponseEntity<?> update(@Valid @RequestBody Customer customer, BindingResult result, @PathVariable Long id) {
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

			Customer customer = this.customerService.findById(id);
			String fileNamePrevious = customer.getImage();

			if (fileNamePrevious != null && fileNamePrevious.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(fileNamePrevious).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}

			this.customerService.delete(id);
			response.put("message", "Cliente eliminado con exito!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "Error al eliminar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(
			@RequestParam("id") Long customerId,
			@RequestParam("image") MultipartFile image) {
		Map<String, Object> response = new HashMap<>();
		try {
			Customer customer = this.customerService.findById(customerId);

			if (!image.isEmpty()) {
				String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename().replace(" ", "");

				Path filePath = Paths.get("uploads").resolve(filename).toAbsolutePath();
				log.info("filePath image:" + filePath.toString());

				try {
					Files.copy(image.getInputStream(), filePath);
				} catch (IOException e) {
					response.put("message", "Error al subir la imagen del cliente " + filename);
					response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				String imagePrevious = customer.getImage();
				if (imagePrevious != null && imagePrevious.length() > 0 && imagePrevious != filename) {
					Path rutaFotoAnterior = Paths.get("uploads").resolve(imagePrevious).toAbsolutePath();
					File archivoFotoAnterior = rutaFotoAnterior.toFile();
					if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
						archivoFotoAnterior.delete();
					}
				}

				customer.setImage(filename);
				customerService.save(customer);

				response.put("customer", customer);
				response.put("message", "Has subido correctamente la imagen: " + filename);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			} else {
				response.put("message", "la imagen es requerida");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "Error al actualizar el cliente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/image/{filename:.+}")
	public ResponseEntity<Resource> imageView(@PathVariable String filename) {

		Path filePath = Paths.get("uploads").resolve(filename).toAbsolutePath();
		Resource resource = null;

		try {
			resource = new UrlResource(filePath.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!resource.exists() || !resource.isReadable()) {
			try {
				filePath = Paths.get("src/main/resources/static/images").resolve("user_not_image.png").toAbsolutePath();
				resource = new UrlResource(filePath.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			log.error("no se pudo cargar la imagen del usuario");
		}
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}

}
