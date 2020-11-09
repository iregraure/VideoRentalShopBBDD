package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Customer;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

	// Servicios
	@Autowired
	private CrudService crudService;

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

	// POST
	@PostMapping(path = "/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer sent) {
		return crudService.addCustomer(sent);
	}

	// PUT
	@PutMapping(path = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer sent) {
		return crudService.updateCustomer(id, sent);
	}

	// DELETE
	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
		return crudService.deleteCustomer(id);
	}

}
