package com.danco.training.dobrilko.task4.model;

public class Reply {
 
	    private Book book;

	    private int numberOfRequests = 0;
	    
	    private boolean executed = false;

	    public int getNumberOfRequests() {
		return numberOfRequests;
	    }

	    public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	    }

	    public Reply(Book book) {
		this.book = book;
		this.numberOfRequests = 1; 

	    }
	    
	    public Reply(Book book, int numberOfRequests) {
		this.book = book;
		this.numberOfRequests = numberOfRequests;

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
	    
	    public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(book.toString());
		sb.append(Integer.toString(numberOfRequests));
		sb.append(";");
		
		return sb.toString();
		
	    
	    }

	
}
