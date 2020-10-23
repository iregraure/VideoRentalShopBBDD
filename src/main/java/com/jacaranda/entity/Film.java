package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Film implements Serializable, Comparable<Film> {
	
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String originalTitle;
	
	private String spanishTitle;
	
	private int year;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="actors", joinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<String> actors;
	
	private boolean rented;
	
	private int duration;
	
	@ManyToOne
	@JoinColumn(name="categoryId", foreignKey = @ForeignKey(name="FK_CATEGORY_FILM"))
	private Category category;
	
	@JoinTable(
			name = "film_rental",
			joinColumns = @JoinColumn(name = "filmId", nullable = false, foreignKey = @ForeignKey(name="FK_FILM")),
			inverseJoinColumns = @JoinColumn(name = "rentalId", nullable = false, foreignKey = @ForeignKey(name="FK_RENTAL")))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
	// Constructor
	public Film() {
		super();
		rented = false;
		rentals = new ArrayList<Rental>();
	}

	public Film(String originalTitle, String spanishTitle, int year, List<String> actors, int duration, Category category) {
		super();
		this.originalTitle = originalTitle;
		this.spanishTitle = spanishTitle;
		this.year = year;
		//this.actors = actors;
		this.duration = duration;
		rented = false;
		this.category = category;
		rentals = new ArrayList<Rental>();
	}

	public String getSpanishTitle() {
		return spanishTitle;
	}

	public void setSpanishTitle(String spanishTitle) {
		this.spanishTitle = spanishTitle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

//	public List<String> getActors() {
//		return actors;
//	}
//
//	public void setActors(List<String> actors) {
//		this.actors = actors;
//	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	@Override
	public int compareTo(Film f1) {
		int resul = this.getOriginalTitle().compareTo(f1.getOriginalTitle());
		if (resul == 0) {
			resul = Integer.valueOf(this.getYear()).compareTo(f1.getYear());
		}
		return resul;
	}

	@Override
	public String toString() {
		return "{\n\tid: " + id + "\n\toriginalTitle: " + originalTitle + "\n\tspanishTitle: " + spanishTitle + "\n\tyear: "
				+ year + "\n\tactors :" + /*actors +*/ "\n\trented :" + rented + "\n\tduration :" + duration + "\n\tcategory :"
				+ category + "\n}";
	}
		
}
