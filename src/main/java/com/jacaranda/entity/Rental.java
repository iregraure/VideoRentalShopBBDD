package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Rental implements Serializable {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double price;
	
	private String startDate;
	
	private String dueDate;
	
	@ManyToOne
	@JoinColumn(name="customerId", foreignKey = @ForeignKey(name="FK_CUSTOMER_RENTAL"))
	private Customer customer;
	
	@JsonIgnoreProperties(value="rentals")
	@ManyToMany(mappedBy = "rentals")
	private List<Film> films;
	
	// Constructor
	public Rental() {
		super();
		films = new ArrayList<Film>();
	}

	public Rental(double price, String startDate, String dueDate, Customer customer, List<Film> films) {
		super();
		this.price = price;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.customer = customer;
		this.films = films;
	}

	// Métodos get y set
	public double getPrice() {
		return price;
	}

	public String getStartDate() {
		return startDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public String getDueDate() {
		return dueDate;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}