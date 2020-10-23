package com.jacaranda.entity;

import java.io.Serializable;
import java.text.ParseException;
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

@Entity
public class Rental implements Serializable {
	
	// Constantes
	private final int DIAS_ALQUILER = 3;
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double price;
	
	private String startDate;
	
	private String dueDate;
	
	@ManyToOne
	@JoinColumn(name="customerId", foreignKey = @ForeignKey(name="FK_CUSTOMER_RENTAL"))
	private Customer customer;
	
	@ManyToMany(mappedBy="rentals")
	private List<Film> films;
	
	// Constructor
	public Rental() {
		super();
		films = new ArrayList<Film>();
	}

	public Rental(double price, String startDate, String dueDate, Customer customer) throws ParseException {
		super();
		this.price = price;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.customer = customer;
		films = new ArrayList<Film>();
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

	public int getId() {
		return id;
	}

	public String getDueDate() {
		return dueDate;
	}

	public List<Film> getFilms() {
		return films;
	}

	@Override
	public String toString() {
		return "{\n\tid : " + id + "\n\tprice : " + price + "\n\tstartDate : " + startDate
				+ "\n\tdueDate : " + dueDate + "\n\tcustomer : " + customer + "\n\tfilms : " + films + "\n}";
	}
}