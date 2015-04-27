package com.danco.training.dobrilko.entity;

import java.util.Date;

import com.danco.training.dobrilko.daoentity.PKHolder;

public class Book implements Cloneable, PKHolder<Integer> {
	private int id;
	private String name;
	private String author;
	private boolean unclaimed;
	private boolean ordered;
	private Date dateOfPublication;
	private Date dateOfAddition;
	private double price;
	private Order order;

	public Book() {

	}

	public Book(String name, String author, double price, int id,
			Date dateOfAddition, Date dateOfPublication, boolean ordered,
			Order order) {
		this.name = name;
		this.ordered = ordered;
		this.author = author;
		this.dateOfAddition = dateOfAddition;
		this.dateOfPublication = dateOfPublication;
		this.id = id;
		this.price = price;
		this.order = order;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfAddition() {
		return dateOfAddition;
	}

	public void setDateOfAddition(Date dateOfAddition) {
		this.dateOfAddition = dateOfAddition;
	}

	public boolean isUnclaimed() {
		return unclaimed;
	}

	public void setUnclaimed(boolean unclaimed) {
		this.unclaimed = unclaimed;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public boolean isOrdered() {
		return ordered;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public Book clone() throws CloneNotSupportedException {
		Book book = new Book();
		book.setAuthor(this.author);
		book.setDateOfAddition((Date) this.dateOfAddition.clone());
		book.setDateOfPublication((Date) this.dateOfPublication.clone());
		book.setName(this.name);
		book.setOrdered(this.ordered);
		book.setPrice(this.price);
		book.setUnclaimed(this.unclaimed);
		book.setId(this.id);
		return book;

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}