package com.danco.training.dobrilko.ModelDB.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.danco.training.dobrilko.ModelDB.primarykey.PKHolder;

@Entity
@Table(name = "book")
public class Book implements PKHolder<Integer> {
	@Column(name = "name")
	private String name;
	@Column(name = "author")
	private String author;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "price")
	private Double price;
	@Column(name = "numberOfExemplars")
	private Integer numberOfExemplars;
	private Boolean outOfStock;
	@Column(name = "publicationDate")
	private Date publicationDate;
	@Column(name = "arrivalDate")
	private Date arrivalDate;
	private Boolean uncalimed;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
	private List<Order> relatedOrders;
	@OneToMany(mappedBy = "book")
	private List<Reply> relatedReplies;

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
