package com.danco.training.dobrilko.entitiy;

import java.io.Serializable;

public class Reply implements Serializable {
	
	private static final long serialVersionUID = 6786433132109699480L;
	private Book book;
	private int id;
	private int numberOfRequests;
	private boolean executed = false;

	public Reply() {
	}

	public Reply(Book book, int numberOfRequests, int id, boolean executed) {
		this.book = book;
		this.numberOfRequests = numberOfRequests;
		this.id = id;

	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

}

