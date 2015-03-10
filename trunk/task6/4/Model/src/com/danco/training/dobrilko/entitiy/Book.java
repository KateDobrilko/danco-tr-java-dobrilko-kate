package com.danco.training.dobrilko.entitiy;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable, Cloneable {
	private static final long serialVersionUID = 731457861374086264L;
	private String name;
	private String author;
	private double price;
	private int id;
	private Date dateOfAddition;
	private Date dateOfPublication;
	private boolean unclaimed;
	private boolean ordered;

	public Book() {

	}

	public Book(String name, String author, double price, int id,
			Date dateOfAddition, Date dateOfPublication, boolean ordered) {
		this.name = name;
		this.ordered = ordered;
		this.author = author;
		this.dateOfAddition = dateOfAddition;
		this.dateOfPublication = dateOfPublication;
		this.id = id;
		this.price = price;

	}

	public Book(String name, String author, double price, int id,
			Date dateOfAddition, Date dateOfPublication) {
		this.name = name;
		this.ordered = false;
		this.author = author;
		this.dateOfAddition = dateOfAddition;
		this.dateOfPublication = dateOfPublication;
		this.id = id;
		this.price = price;
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

	public int getId() {
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
}
