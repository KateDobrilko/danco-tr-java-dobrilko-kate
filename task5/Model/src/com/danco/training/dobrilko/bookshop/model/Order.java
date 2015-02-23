package com.danco.training.dobrilko.bookshop.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order {

	private int id;
	private ArrayList<Book> books = new ArrayList<Book>();
	private Calendar date;
	private boolean status; // true - in progress, false - executed

	public Order() {

	}

	public Order(int id, ArrayList<Book> books) {
		this.id = id;
		this.books = books;
		this.date = new GregorianCalendar(0, 0, 0);
		this.status = true;

	}

	public Order(int id, ArrayList<Book> books, int day, int month, int year) {
		this.id = id;
		this.books = books;

		this.date = new GregorianCalendar(year, month, day);
		this.status = false;

	}

	public double getSum() {
		double sum = 0;
		for (Book book : books) {
			sum += book.getPrice();
		}
		return sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ");
		sb.append(Integer.toString(id));
		sb.append("; ");
		sb.append("Number Of Books: ");
		sb.append(Integer.toString(books.size()));
		sb.append(";");
		sb.append("Date Of Order: ");
		if (!(date.get(Calendar.YEAR) == (2))) {
			sb.append(Integer.toString(date.get(Calendar.DATE)));
			sb.append("/");
			sb.append(Integer.toString(date.get(Calendar.MONTH)));
			sb.append("/");
			sb.append(Integer.toString(date.get(Calendar.YEAR)));

		} else {
			sb.append("not executed");
		}
		sb.append(System.lineSeparator());
		books.forEach((Book book) -> sb.append(book.toString() + System.lineSeparator()));

		return sb.toString();
	}
}
