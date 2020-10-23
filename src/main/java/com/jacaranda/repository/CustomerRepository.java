package com.jacaranda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	// Get de un customer por nombre
	public  List<Customer> findCustomerByFullName(String name);
	
	// Get de un customer por DNI
	public Customer findCustomerByDni(String dni);
	
	// Get de un customer por id
	public Customer findCustomerById(int id);
	
	// Get de todos los customer ordenado por nombre
	@Query(value = "select * from customer order by full_name", nativeQuery = true)
	public List<Customer> findAllOrderedByName();
}
