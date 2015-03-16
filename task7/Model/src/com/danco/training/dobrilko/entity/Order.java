package com.danco.training.dobrilko.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.danco.training.dobrilko.annotation.CSVCompositeList;
import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.bookshop.Bookshop;
import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;
import com.danco.training.dobrilko.interfaceholder.HasId;

@CSVEntity(csvPath = CSVFileReflectionPath.ORDER_REFLECTION_PATH)
public class Order implements Serializable, Cloneable, HasId {
	private static final long serialVersionUID = 8844590591157079914L;
	@CSVPrimitiveProperty(positionInString = 0)
	private int id;
	@CSVCompositeList(clazzOfMembers = Book.class)
	private ArrayList<Book> books = new ArrayList<Book>();
	@CSVPrimitiveProperty(positionInString = 1)
	private int numberOfBooks;
	@CSVPrimitiveProperty(positionInString = 2)
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

	public Order(ArrayList<Integer> bookIds, int id, boolean status,
			Date dateOfExecution) {
		this.setId(id);
		this.setDateOfExecution(dateOfExecution);
		this.setStatus(true);
		for (Integer bookId : bookIds) {
			this.books.add(Bookshop.getInstance().getBookBase()
					.getById((int) bookId));
		}
		this.setNumberOfBooks(bookIds.size());

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
		Order order = (Order) super.clone();
		order.setBooks(new ArrayList<Book>());		
		return order;

	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}
}
