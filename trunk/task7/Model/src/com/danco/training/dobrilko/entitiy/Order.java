package com.danco.training.dobrilko.entitiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.danco.training.dobrilko.annotation.CSVCompositeList;
import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;
import com.danco.training.dobrilko.interfaceholder.HasId;
@CSVEntity(csvPath = CSVFileReflectionPath.OrderReflectionPath)
public class Order implements Serializable, Cloneable, HasId {
	private static final long serialVersionUID = 8844590591157079914L;
	@CSVPrimitiveProperty
	private int id;
	@CSVCompositeList
	private ArrayList<Book> books = new ArrayList<Book>();
	@CSVPrimitiveProperty
    private int numberOfBooks;
	@CSVPrimitiveProperty
	private Date dateOfExecution;
	
	private boolean status; // true - in progress, false - executed

	public Order() {

	}

	public Order(int id, ArrayList<Book> books, boolean status,
			Date dateOfExecution) {
		this.setId(id);
		this.setBooks(books);
		this.setDateOfExecution(dateOfExecution);
		this.setStatus(true);
		this.setNumberOfBooks(books.size());

	}

	public double getSum() {
		double sum = 0;
		for (Book book : books) {
			sum += book.getPrice();
		}
		return sum;
	}

	public ArrayList<Book> getBooks() {
		
		return books;
		
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
		this.setNumberOfBooks(books.size());
	}

	public Date getDateOfExecution() {
		return dateOfExecution;
	}

	public void setDateOfExecution(Date dateOfExecution) {
		this.dateOfExecution = dateOfExecution;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order clone() throws CloneNotSupportedException {
		Order order = new Order();
		ArrayList<Book> books = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			books.add(book.clone());
		}
		order.setBooks(books);
		if (this.getDateOfExecution() != null) {
			order.setDateOfExecution((Date) this.getDateOfExecution().clone());
		} else {
			order.setDateOfExecution(null);
		}
		order.setId(this.getId());
		order.setStatus(this.getStatus());
		return order;

	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
}
