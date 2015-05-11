package com.danco.training.dobrilko.ModelDB.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.danco.training.dobrilko.ModelDB.primarykey.PKHolder;
@Entity
@Table(name = "order")
public class Order implements PKHolder<Integer>{
	@Column(name = "executionDate")
	private Date executionDate;
	@Column(name = "executed")
	private Boolean executed;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "sum")
	private Double sum;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "order_to_book", joinColumns = { 
			@JoinColumn(name = "idO", nullable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idB", nullable = false) })
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
