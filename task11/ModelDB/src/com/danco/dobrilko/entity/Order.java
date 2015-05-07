package com.danco.dobrilko.entity;

import java.util.ArrayList;
import java.util.Date;

import com.danco.dobrilko.primarykey.PKHolder;

public class Order implements PKHolder<Integer>{
	private Date executionDate;
	private Boolean executed;
	private Integer id;
	private Double sum;
	private ArrayList<Book> books;

	public Order()  {

	}

	public Order(Date executionDate, ArrayList<Book> books, Integer id, Double sum) {
		this.executionDate = executionDate;
		this.books = books;
		this.setId(id);
		this.sum = sum;
		this.executed = (executionDate != null);

	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

}
