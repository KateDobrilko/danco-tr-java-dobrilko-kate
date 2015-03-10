package com.danco.training.database;

import java.io.Serializable;
import java.util.ArrayList;

import com.danco.training.dobrilko.entitiy.Book;
import com.danco.training.dobrilko.entitiy.Order;

public class OrderBase implements Serializable {

	private static final long serialVersionUID = -8789135438281397005L;
	private ArrayList<Order> orders;

	public OrderBase() {
		this.setOrders(new ArrayList<Order>());
	}

	public OrderBase(ArrayList<Order> orders) {
		this.setOrders(orders);
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public boolean add(Order order) {

		boolean idUnique = true;
		for (Order o : this.orders) {
			if (o.getId() == order.getId()) {
				idUnique = false;
				update(o.getId(), order);
				break;
			}
		}
		if (idUnique) {
			for (Book book : order.getBooks()) {
				book.setOrdered(true);
			}
			this.orders.add(order);
		}

		return idUnique;
	}

	public boolean delete(int id) {
		boolean idUnique = true;
		for (Order o : this.orders) {
			if (o.getId() == id) {
				idUnique = false;
				break;
			}
		}
		if (idUnique) {
			this.orders.removeIf((Order order) -> order.getId() == id);
		}

		return idUnique;

	}

	public boolean update(int id, Order order) {
		boolean idMatches = false;
		for (Order o : this.orders) {
			if (o.getId() == id) {
				idMatches = true;
				o = order;
				break;
			}
		}
		return idMatches;
	}

	public Order getById(int id) {
		Order order = new Order();
		for (Order o : this.orders) {
			if (o.getId() == id) {
				order = o;
				break;
			}
		}
		return order;
	}
}
