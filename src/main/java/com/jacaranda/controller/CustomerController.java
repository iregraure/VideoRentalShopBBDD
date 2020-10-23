package com.jacaranda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
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

import com.jacaranda.entity.Customer;
import com.jacaranda.repository.CustomerRepository;
import com.jacaranda.services.SearchService;
import com.jacaranda.services.SortService;
import com.jacaranda.services.UpdateService;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

	// Inicializamos el array
	private List<Customer> customers = new ArrayList<>();

	// Serviciossss
	@Autowired
	private UpdateService updateService;

	// Repositorios
	@Autowired
	private CrudRepository<Customer, Integer> customerRepository;
	
	@Autowired
	private CustomerRepository customerRepository2;

	// GET
	// All customers
	@GetMapping(path = "/customer")
	public ResponseEntity<?> getCustomers() {
		ResponseEntity<?> response = ResponseEntity.ok(customerRepository.findAll());
		return response;
	}

	// All customers sorted
	@GetMapping(path = "/customerOrdered")
	public ResponseEntity<?> getCustomersOrdered() {
		ResponseEntity<?> response = ResponseEntity.ok(customerRepository2.findAllOrderedByName());
		return response;
	}

	// Customer by Name
	@GetMapping(path = "/customer/{name}")
	public ResponseEntity<?> getCustomer(@PathVariable String name) {
		ResponseEntity<?> response;
		if (customers.isEmpty()) {
			
		} try {
			response = ResponseEntity.ok(customerRepository2.findCustomerByFullName(name));
		}
		catch (Exception e) {
			response = ResponseEntity.notFound().build();
		}
		return response;
	}

	// POST
	@PostMapping(path = "/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer sent) {
		String dniSent = sent.getDni();
		Customer c = customerRepository2.findCustomerByDni(dniSent);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.ok(customerRepository.save(sent));
		} else {
			response = ResponseEntity.badRequest().body("Customer already exists");
		}
		return response;
	}

	// PUT
	@PutMapping(path = "/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer sent) {
		Customer c = customerRepository2.findCustomerById(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.notFound().build();
		} else {
			updateService.updateCustomer(c, sent);
			response = ResponseEntity.ok(customerRepository.save(c));
		}
		return response;
	}

	// DELETE
	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
		Customer c = customerRepository2.findCustomerById(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.notFound().build();
		} else {
			customerRepository.deleteById(id);
			response = ResponseEntity.ok("Customer deleted");
		}
		return response;
	}

}
