package com.jacaranda.services;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.jacaranda.entity.Rental;

@Service
public class QueryService {

	@PersistenceContext
	private EntityManager entityManager;

	// Método para buscar un rental por fecha de inicio y cliente
	public Rental findRentalByDateAndCustomer(String date, int idClient) {
		Query query = entityManager.createNativeQuery("Select * from Rental where startDate like ?1 and customerId = ?2", Rental.class);
		query.setParameter(1, date);
		query.setParameter(2, idClient);
		Stream<Rental> rentals = query.getResultStream();
		entityManager.close();
		return rentals.findAny().orElse(null);
	}

}
