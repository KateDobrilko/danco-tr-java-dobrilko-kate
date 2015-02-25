package com.danco.training.dobrilko.bookshop.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.danco.training.dobrilko.bookshop.comparator.OrderDateComparator;
import com.danco.training.dobrilko.bookshop.comparator.OrderExecutedComparator;
import com.danco.training.dobrilko.bookshop.comparator.OrderPriceComparator;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import org.apache.log4j.Logger;

public class OrderingSystem {
	public Logger logger = Logger.getLogger(OrderingSystem.class);
	private ArrayList<Order> orders = new ArrayList<Order>();

	public OrderingSystem() {

	}

	public OrderingSystem(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void executeOrder(int orderId, Store store) {

		Order orderToExecute = null;
		for (Order order : orders) {

			if (order.getId() == orderId) {
				order.setStatus(false);
				order.setDate(Calendar.getInstance());
				for (Book orderBook : order.getBooks()) {
					for (Book storeBook : store.getBooks()) {
						if ((orderBook.getAuthor().equals(storeBook.getAuthor())) && (orderBook.getName().equals(storeBook.getName())) && (orderBook.getPrice() == storeBook.getPrice()) && (storeBook.isInStore())) {

							store.deleteBook(storeBook.getId());
							order.setStatus(false);
							order.setDate(Calendar.getInstance());
							orderToExecute = order;
							break;
						}
					}

				}
			}
		}
		if (orderToExecute == null) {
			logger.warn("Order with id:" + Integer.toString(orderId) + " doesn't exist or already executed. No execution performed.");
		}
	}

	public void sortByDate() {
		orders.sort(new OrderDateComparator());
	}

	public void sortByExecution() {
		orders.sort(new OrderExecutedComparator());
	}

	public void sortByPrice() {
		orders.sort(new OrderPriceComparator());
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void deleteOrder(Order order) {
		if (orders.contains(order)) {
			orders.remove(order);
		} else {

			logger.warn("Order with id " + order.getId() + "doesn't exist.");

		}
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<Order> getExecutedOrders() {
		ArrayList<Order> executed = new ArrayList<Order>();
		for (Order order : orders) {
			if (!order.getStatus()) {
				executed.add(order);
			}
		}
		return executed;
	}

	public ArrayList<Order> getExecutedOrders(int startYear, int startMonth, int endYear, int endMonth) {
		ArrayList<Order> executed = new ArrayList<Order>();
		for (Order order : getExecutedOrders()) {
			boolean condition1 = (order.getDate().get(Calendar.MONTH) + order.getDate().get(Calendar.YEAR) * 12) > (startMonth + startYear * 12);
			boolean condition2 = (order.getDate().get(Calendar.MONTH) + (order.getDate().get(Calendar.YEAR) * 12)) < (endMonth + endYear * 12);
			if ((condition1 == true) && (condition2 == true)) {
				executed.add(order);
			}
			;
		}
		return executed;
	}

	public double getSumOfExecutedOrders(int startYear, int startMonth, int endYear, int endMonth) {
		double sum = 0;

		for (Order order : getExecutedOrders()) {

			boolean condition1 = (order.getDate().get(Calendar.MONTH) + order.getDate().get(Calendar.YEAR) * 12) > (startMonth + startYear * 12);
			boolean condition2 = (order.getDate().get(Calendar.MONTH) + (order.getDate().get(Calendar.YEAR) * 12)) < (endMonth + endYear * 12);
			if ((condition1 == true) && (condition2 == true))
				sum += order.getSum();
		}
		return sum;
	}

	public int getNumberOfExecutedOrders(int startYear, int startMonth, int endYear, int endMonth) {

		return getExecutedOrders(startYear, startMonth, endYear, endMonth).size();
	}

	public void cancelOrder(int id) {
		Order orderToDelete = null;
		for (Order order : orders) {
			if (order.getId() == id) {
				if (order.getStatus()) {
					orderToDelete = order;
					deleteOrder(order);
					break;
				} else {
					logger.warn("Order with id:" + Integer.toString(id) + " is already executed.");

				}
			}
			setOrders(orders);
		}
		if (orderToDelete == (null)) {
			logger.warn("Order with id:" + Integer.toString(id) + " doesn't exist.");
		}
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(orders.size()));
		sb.append(System.lineSeparator());
		orders.forEach((Order order) -> sb.append(order.toString()));

		return sb.toString();
	}

}
