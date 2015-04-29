package com.danco.training.dobrilko.entity;

import com.danco.training.dobrilko.daoentity.PKHolder;

public class Reply implements PKHolder<Integer> {

	private int id;
	private int numberOfRequests;
	private boolean executed = false;
	private Book book;

	public Reply() {
	}

	public Reply( int numberOfRequests, int id,
			boolean executed, Book book) {
		this.book = book;
		this.numberOfRequests = numberOfRequests;
		this.id = id;
		this.executed = executed;

	}

	public Integer getId() {
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
