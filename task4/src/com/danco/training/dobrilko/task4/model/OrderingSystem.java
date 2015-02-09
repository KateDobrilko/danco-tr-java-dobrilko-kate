package com.danco.training.dobrilko.task4.model;

import java.util.ArrayList;

import com.danco.training.dobrilko.task4.comparator.OrderDateComparator;
import com.danco.training.dobrilko.task4.comparator.OrderExecutedComparator;
import com.danco.training.dobrilko.task4.comparator.OrderPriceComparator;

public class OrderingSystem {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public OrderingSystem() {

    }

    public OrderingSystem(ArrayList<Order> orders) {
	this.orders = orders;
    }

    public void executeOrder(int orderId, Cashier cashier, Store store) {
	for (Order order : orders) {

	    if (order.getId() == orderId) {
		order.setExecuted(true);
		for (BookType bookType : order.getBookOrder()) {
		    cashier.addSum(bookType.getPrice());
		}
	    }
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

    public ArrayList<Order> getOrders() {
	return orders;
    }

    public ArrayList<Order> getExecutedOrders() {
	ArrayList<Order> executed = new ArrayList<Order>();
	for (Order order : orders) {
	    if (order.isExecuted()) {
		executed.add(order);
	    }
	}
	return executed;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("OrderingSystem " + System.lineSeparator() + orders.size()
		+ System.lineSeparator());
	for (Order order : orders) {
	    sb.append(order.toString() + System.lineSeparator());
	}
	return sb.toString();

    }

}
