package com.danco.training.dobrilko.ModelDB.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.danco.training.dobrilko.ModelDB.primarykey.PKHolder;

@Entity
@Table(name = "reply")
public class Reply implements PKHolder<Integer> {
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	@Column(name = "number")
	private Integer number;
	@Column(name = "executed")
	private Boolean executed;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	public Reply() {

	}

	public Reply(Integer id, Integer idBook, Integer number, Boolean executed) {

		this.number = number;
		this.executed = executed;

		this.book = new Book();

	}

	public Reply(Integer id, Book book, Integer number, Boolean executed) {

		this.number = number;
		this.executed = executed;
		this.book = book;

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

}
