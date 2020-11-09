package com.jacaranda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer> {

	// Get de un rental por id
	public Rental findRentalById(int id);

	// Get de un rental por startDate
	public Rental findRentalByStartDate(String date);

	// Get de un rental por customer
	public List<Rental> findRentalByCustomer(Customer customer);

}