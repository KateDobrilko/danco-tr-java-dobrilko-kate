package com.danco.training.dobrilko.task4.model;


public class Reply {
    private BookType bookType;

    private int numberOfRequests = 0;
    
    private boolean executed = false;

    public Reply(BookType bookType) {
	this.bookType = bookType;

    }
    
    public Reply(BookType bookType, int numberOfRequests) {
	this.bookType = bookType;
	this.numberOfRequests = numberOfRequests;

    }

    public BookType getBookType() {
	return bookType;
    }

    public void setBookTypes(BookType bookType) {
	this.bookType = bookType;
    }

    public void iterateNumberOfRequests() {
	this.numberOfRequests++;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
        sb.append("Reply"+System.lineSeparator());
	sb.append(bookType.toString());
	sb.append("|");
	sb.append(numberOfRequests);

	return sb.toString();
    }

    public boolean isExecuted() {
	return executed;
    }

    public void setExecuted(boolean executed) {
	this.executed = executed;
    }

}
