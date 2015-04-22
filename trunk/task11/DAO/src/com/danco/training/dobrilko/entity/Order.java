package com.danco.training.dobrilko.entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.dao.DaoFactory;
import com.danco.training.dobrilko.dao.GenericDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.daoentity.Identified;
import com.danco.training.dobrilko.mysql.MySqlDaoFactory;

public class Order implements Serializable, Cloneable, Identified<Integer> {
	private static final long serialVersionUID = 8844590591157079914L;
	private int id;
	private Integer bookId;
	private Date dateOfExecution;
	private boolean status; // true - in progress, false - executed

	public Order() {

	}

	public Order(int id, Integer bookIds, boolean status, Date dateOfExecution) {
		this.setId(id);
		this.setBookId(bookId);
		this.setDateOfExecution(dateOfExecution);
		if (dateOfExecution == null) {
			this.setStatus(true);
		} else {
			this.setStatus(false);
		}

	}

	public double getSum() {
		double sum = 0;
		DaoFactory<Connection> factory = new MySqlDaoFactory();
		Connection connection;
		try {
			connection = factory.getContext();
			connection.setAutoCommit(false);

			@SuppressWarnings("unchecked")
			GenericDao<Book, Integer> dao = (GenericDao<Book, Integer>) (factory
					.getDao(connection, Book.class));
			Book book = (Book) dao.getByPK(this.getBookId());
			sum = book.getPrice();
		} catch (SQLException | PersistException e) {
			Logger logger = Logger.getLogger(Order.class);
			logger.error(e);
		}

		return sum;
	}

	public Integer getBookId() {

		return bookId;

	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;

	}

	public Date getDateOfExecution() {
		return dateOfExecution;
	}

	public void setDateOfExecution(Date dateOfExecution) {
		this.dateOfExecution = dateOfExecution;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order clone() throws CloneNotSupportedException {
		Order order = (Order) super.clone();
		return order;

	}

}
