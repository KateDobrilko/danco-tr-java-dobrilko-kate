package com.danco.dobrilko.entity;

import java.util.Date;

import com.danco.dobrilko.primarykey.PKHolder;

public class Book implements PKHolder<Integer> {
	private String name;
	private String author;
	private Integer id;
	private Double price;
	private Integer numberOfExemplars;
	private Boolean outOfStock;
	private Date publicationDate;
	private Date arrivalDate;
	private Boolean uncalimed;
	public Book() {

	}

	public Book(String name, String author, Double price,
			Integer numberOfExemplars, Integer id, Date publicationDate,
			Date arrivalDate) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.setId(id);
		this.numberOfExemplars = numberOfExemplars;
		if (arrivalDate == null) {
			this.numberOfExemplars = 0;
		}
		this.outOfStock = (numberOfExemplars == 0);
		this.arrivalDate = arrivalDate;
		this.publicationDate = publicationDate;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getNumberOfExemplars() {
		return numberOfExemplars;
	}

	public void setNumberOfExemplars(Integer numberOfExemplars) {
		this.numberOfExemplars = numberOfExemplars;
	}

	public Boolean getOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(Boolean outOfStock) {
		this.outOfStock = outOfStock;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getUncalimed() {
		return uncalimed;
	}

	public void setUncalimed(Boolean uncalimed) {
		this.uncalimed = uncalimed;
	}

}
