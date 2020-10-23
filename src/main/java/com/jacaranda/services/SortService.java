package com.jacaranda.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Film;
import com.jacaranda.entity.comparator.*;
@Service
public class SortService {
	
	public List<Customer> orderCustomers(List<Customer> customers, String param){
		if(param.equalsIgnoreCase("fullName")) {
			Collections.sort(customers, new ComparatorCustomerFullName());
		}
		else {
			Collections.sort(customers, new ComparatorCustomerId());
		}
		return customers;
	}
	
	public List<Film> orderFilms(List<Film> films, String param){
		if(param.equalsIgnoreCase("originalTitle")) {
			Collections.sort(films, new ComparatorFilmOriginalTitle());
		}
		else if(param.equalsIgnoreCase("year")) {
			Collections.sort(films, new ComparatorFilmYear());
		}
		else {
			Collections.sort(films, new ComparatorFilmId());
		}
		return films;
	}
	
}
