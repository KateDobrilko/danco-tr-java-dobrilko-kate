package com.danco.training.dobrilko.entity;

import java.io.Serializable;
import java.util.Date;

import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;
import com.danco.training.dobrilko.interfaceholder.HasId;
@CSVEntity(csvPath = CSVFileReflectionPath.BOOK_REFLECTION_PATH)
public class Book implements Serializable, Cloneable, HasId {
	private static final long serialVersionUID = 731457861374086264L;
	@CSVPrimitiveProperty(positionInString = 0)
	private int id;
	@CSVPrimitiveProperty(positionInString = 1)
	private String name;
	@CSVPrimitiveProperty(positionInString = 2)
	private String author;
	@CSVPrimitiveProperty(positionInString = 4)
	private boolean unclaimed;
	@CSVPrimitiveProperty(positionInString = 5)
	private boolean ordered;
	@CSVPrimitiveProperty(positionInString = 6)
	private int numberOfExemplars;
	@CSVPrimitiveProperty(positionInString = 7)
	private Date dateOfPublication;
	@CSVPrimitiveProperty(positionInString = 10)
	private Date dateOfAddition;
	@CSVPrimitiveProperty(positionInString = 3)
	private double price;
	public Book() {

	}

	public Book(String name, String author, double price, int id,
			Date dateOfAddition, Date dateOfPublication, boolean ordered, int numberOfExemplars) {
		this.name = name;
		this.ordered = ordered;
		this.author = author;
        this.numberOfExemplars = numberOfExemplars;
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
		this.numberOfExemplars = 0;
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

	public void setNumberOfExemplars(int num) {
		this.numberOfExemplars = num;
	}
	
	public int getNumberOfExemplars() {
		return this.numberOfExemplars;
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
