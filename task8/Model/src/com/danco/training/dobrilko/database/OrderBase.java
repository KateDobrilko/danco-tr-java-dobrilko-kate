package com.danco.training.dobrilko.database;

import java.io.Serializable;
import java.util.ArrayList;

import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;

public class OrderBase implements Serializable {

	private static final long serialVersionUID = -8789135438281397005L;
	
	private Order[] ordersArray = new Order[0];
	private ArrayList<Order> orders;

	public OrderBase() {
		this.setOrders(new ArrayList<Order>());
		ordersArray = new Order[orders.size()];
		ordersArray = orders.toArray(ordersArray);
	}

	public OrderBase(ArrayList<Order> orders) {
		this.setOrders(orders);
		ordersArray = new Order[orders.size()];
		ordersArray = orders.toArray(ordersArray);

	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
		orders.toArray(ordersArray);
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
			ordersArray = new Order[orders.size()];
			ordersArray = orders.toArray(ordersArray);
		}

		return idUnique;
	}

	public boolean delete(int id) {
		boolean idUnique = false;
		for (Order o : this.orders) {
			if (o.getId() == id) {
				this.orders.removeIf((Order order) -> order.getId() == id);
				ordersArray = new Order[orders.size()];
				ordersArray = orders.toArray(ordersArray);
				idUnique = true;
				break;
			}
		}
		if (idUnique) {
			

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
		ordersArray = new Order[orders.size()];
		ordersArray = orders.toArray(ordersArray);

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

	public Order[] getOrdersArray() {
		return ordersArray;
	}

}
