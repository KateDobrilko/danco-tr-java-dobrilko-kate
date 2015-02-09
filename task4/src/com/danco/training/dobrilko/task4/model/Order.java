package com.danco.training.dobrilko.task4.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
    private int id;
    private ArrayList<BookType> books = new ArrayList<BookType>();
    private Calendar date;
    private boolean executed;
    private Customer customer;

    public Order(int id, ArrayList<BookType> bookOrder, Calendar date,
	    Customer customer) {
	this.id = id;
	this.books = bookOrder;
	this.date = date;
	this.customer = customer;
	this.setExecuted(false);
    }

    public int getId() {
	return id;
    }

    public double getSum() {
	double sum = 0;
	for (BookType book : books) {
	    sum += book.getPrice();
	}
	return sum;
    }

    public ArrayList<BookType> getBookOrder() {
	return books;
    }

    public Calendar getDate() {
	return date;
    }

    public Customer getCustomer() {
	return customer;
    }

    public boolean isExecuted() {
	return executed;
    }

    public void setExecuted(boolean executed) {
	this.executed = executed;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append(id);
	sb.append(System.lineSeparator());
	sb.append(customer.toString());
	sb.append(System.lineSeparator());
	sb.append(Integer.toString(date.get(Calendar.YEAR)) + "/"
		+ Integer.toString(date.get(Calendar.MONTH)) + "/"
		+ Integer.toString(date.get(Calendar.DATE)));

	sb.append(System.lineSeparator());

	sb.append(Boolean.toString(executed));
	sb.append(System.lineSeparator());
	sb.append(books.size());

	sb.append(System.lineSeparator());

	for (BookType bookType : books) {
	    sb.append(bookType.toString() + System.lineSeparator());
	    if (bookType instanceof BookExemplar) {
		sb.append(((BookExemplar) bookType).toString()
			+ System.lineSeparator());
	    }
	}

	return sb.toString();
    }

}
