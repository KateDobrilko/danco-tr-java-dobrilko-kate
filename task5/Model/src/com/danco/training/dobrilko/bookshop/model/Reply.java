package com.danco.training.dobrilko.bookshop.model;

public class Reply {

	private Book book;
	private int id;
	private int numberOfRequests = 0;

	private boolean executed = false;

	public int getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

	public Reply(Book book, int id) {
		this.book = book;
		this.numberOfRequests = 1;
		this.id = id;

	}

	public Reply() {
	}

	public Reply(Book book, int numberOfRequests, int id) {
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

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ");
		sb.append(Integer.toString(id));
		sb.append(";");
		sb.append(book.toString());
		sb.append("Number Of Requests: ");
		sb.append(Integer.toString(numberOfRequests));
		sb.append(";");

		return sb.toString();

	}

	public int getId() {
		return id;
	}

}
