package com.danco.training.dobrilko.entitiy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable, Cloneable {
	private static final long serialVersionUID = 8844590591157079914L;
	private int id;
	private ArrayList<Book> books = new ArrayList<Book>();
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
}
