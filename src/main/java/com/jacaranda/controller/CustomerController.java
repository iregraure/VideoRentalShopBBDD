package com.jacaranda.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.jacaranda.entity.Customer;
import com.jacaranda.services.CrudService;
import com.jacaranda.services.FileTransferService;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

	// Servicios
	@Autowired
	private CrudService crudService;
	
	@Autowired
	private FileTransferService ftService;

	// GET
	// All customers
	@GetMapping(path = "/customer")
	public ResponseEntity<?> getCustomers() {
		return crudService.getAllCustomer();
	}

	// All customers sorted
	@GetMapping(path = "/customerOrdered")
	public ResponseEntity<?> getCustomersOrdered() {
		return crudService.getAllCustomerOrdered();
	}

	// Customer by Name
	@GetMapping(path = "/customer/{name}")
	public ResponseEntity<?> getCustomer(@PathVariable String name) {
		return crudService.getCustomerByName(name);
	}
	
	// Download picture
	@GetMapping(path = "/customer/{id}/picture")
	public ResponseEntity<?> downloadPicture(@PathVariable int id) throws SQLException {
		return ftService.downloadPicture(id);
	}

	// POST
	@PostMapping(path = "/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer sent) {
		return crudService.addCustomer(sent);
	}

	// PUT
	// Actualizar datos del cliente
	@PutMapping(path = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer sent) {
		return crudService.updateCustomer(id, sent);
	}
	
	// Añadir una foto
	@PutMapping(path = "/customer/{id}/picture")
	public ResponseEntity<?> addPicture(@PathVariable int id, @RequestParam MultipartFile mpf) {
		return ftService.addPicture(mpf, id);
	}

	// DELETE
	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
		return crudService.deleteCustomer(id);
	}

}
