package com.jacaranda.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jacaranda.entity.enums.CategoryType;

@Entity
public class Category implements Serializable {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private CategoryType type;
	
	private String description;
	
	// Constructor
	public Category() {
		super();
	}

	public Category(CategoryType type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	// Métodos get y set
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public CategoryType getType() {
		return type;
	}
	
}
