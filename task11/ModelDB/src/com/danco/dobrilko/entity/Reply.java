package com.danco.dobrilko.entity;

import com.danco.dobrilko.primarykey.PKHolder;

public class Reply implements PKHolder<Integer> {
	private Book book;
	private Integer number;
	private Boolean executed;
	private Integer id;
	private Integer bookId;

	public Reply() {

	}

	public Reply(Integer id, Integer idBook, Integer number, Boolean executed) {

		this.number = number;
		this.executed = executed;
		this.bookId = idBook;
		this.book = new Book();

	}

	public Reply(Integer id, Book book, Integer number, Boolean executed) {

		this.number = number;
		this.executed = executed;
		this.book = book;
		this.bookId = book.getId();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
}
