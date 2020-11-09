package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Rental;
import com.jacaranda.services.CrudService;

@RestController
@RequestMapping(path = "/api")
public class RentalController {
	
	// Servicios
	@Autowired
	private CrudService crudService;

	// GET
	// All rentals
	@GetMapping(path = "/rental")
	public ResponseEntity<?> getRentals() {
		return crudService.getAllRental();
	}

	// Rental by Id
	@GetMapping(path = "/rental/{id}")
	public ResponseEntity<?> getRental(@PathVariable int id) {
		return crudService.getRentalById(id);
	}
	
	// Rental by Customer
	@GetMapping(path = "rentals/{id}")
	public ResponseEntity<?> getRentalsCustomer(@PathVariable int id) {
		return crudService.getRentalByCustomerId(id);
	}

	// POST
	@PostMapping(path = "/rental")
	public ResponseEntity<?> addRental(@RequestBody Rental sent) {
		return crudService.addRental(sent);
	}

}
