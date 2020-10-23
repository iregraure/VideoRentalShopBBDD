package com.jacaranda.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable, Comparable<Customer> {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String fullName;
	
	private String address;
	
	private String phoneNumber;
	
	private String birthDate;
	
	private String dni;

	// Constructores
	public Customer() {
		super();
	}

	public Customer(String fullName, String address, String phoneNumber, String birthDate, String dni) {
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.dni = dni;
	}

	// Métodos get y set
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public int compareTo(Customer c1) {
		return this.getDni().compareTo(c1.getDni());
	}

	@Override
	public String toString() {
		return "{\n\tid : " + id + ", \n\tfullName : " + fullName + ",\n\taddress : " + address
				+ ", \n\tphoneNumber : " + phoneNumber + ", \n\tbirthDate : " + birthDate + ", \n\tdni : "
				+ dni + "\n}";
	}

}
