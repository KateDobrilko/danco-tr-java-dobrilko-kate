package com.danco.training.dobrilko.entity;

import java.io.Serializable;

import com.danco.training.dobrilko.annotation.CSVCompositeProperty;
import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.bookshop.Bookshop;
import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;
@CSVEntity(csvPath = CSVFileReflectionPath.REPLY_REFLECTION_PATH)
public class Reply implements Serializable {
	
	private static final long serialVersionUID = 6786433132109699480L;

	@CSVPrimitiveProperty(positionInString = 0)
	private int id;
	@CSVPrimitiveProperty(positionInString = 1)
	private int numberOfRequests;
	@CSVPrimitiveProperty(positionInString = 2)
	private boolean executed = false;
	@CSVCompositeProperty(getIdFunction = "getId")
	private Book book;

	public Reply() {
	}

	public Reply(Book book, int numberOfRequests, int id, boolean executed) {
		this.book = book;
		this.numberOfRequests = numberOfRequests;
		this.id = id;
		this.executed = executed;

	}

	public Reply(int bookId, int numberOfRequests, int id, boolean executed) {
		this.book = Bookshop.getInstance().getBookBase().getById(bookId);
		this.numberOfRequests = numberOfRequests;
		this.id = id;
		this.executed = executed;

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

