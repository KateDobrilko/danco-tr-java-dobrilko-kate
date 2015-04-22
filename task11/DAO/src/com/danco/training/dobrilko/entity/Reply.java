package com.danco.training.dobrilko.entity;

import java.io.Serializable;

import com.danco.training.dobrilko.daoentity.Identified;

public class Reply  implements Serializable, Identified<Integer> {

	private static final long serialVersionUID = 6786433132109699480L;

	private int id;
	private int numberOfRequests;
	private boolean executed = false;
	private Integer bookId;

	public Reply() {
	}

	public Reply(Integer bookId, int numberOfRequests, int id, boolean executed) {
		this.bookId = bookId;
		this.numberOfRequests = numberOfRequests;
		this.id = id;
		this.executed = executed;

	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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

}
