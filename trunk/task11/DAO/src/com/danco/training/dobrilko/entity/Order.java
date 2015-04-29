package com.danco.training.dobrilko.entity;

import java.util.Date;
import com.danco.training.dobrilko.daoentity.PKHolder;

public class Order implements Cloneable, PKHolder<Integer> {

	private int id;
	private Date dateOfExecution;
	private boolean status; // true - in progress, false - executed
	private double sum;

	public Order() {

	}

	public Order(int id, Date dateOfExecution, Double sum) {
		this.setId(id);
		this.setDateOfExecution(dateOfExecution);
		if (dateOfExecution == null) {
			this.setStatus(true);
		} else {
			this.setStatus(false);
		}
		this.sum = sum;

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

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

}
