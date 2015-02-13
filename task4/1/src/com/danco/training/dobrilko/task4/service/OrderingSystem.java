package com.danco.training.dobrilko.task4.service;

import java.util.ArrayList;
import java.util.Calendar;

import com.danco.training.dobrilko.task4.model.Book;
//import com.danco.training.dobrilko.task4.model.Cashier;
import com.danco.training.dobrilko.task4.model.Order;
import com.danco.training.dobrilko.task4.comparator.OrderDateComparator;
import com.danco.training.dobrilko.task4.comparator.OrderExecutedComparator;
import com.danco.training.dobrilko.task4.comparator.OrderPriceComparator;
import com.danco.training.dobrilko.task4.service.Store;

public class OrderingSystem {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public OrderingSystem() {

    }

    public OrderingSystem(ArrayList<Order> orders) {
	this.orders = orders;
    }

    public void executeOrder(int orderId, Store store) {
	for (Order order : orders) {

	    if (order.getId() == orderId) {
		order.setStatus(false);
		order.setDate(Calendar.getInstance());
		for (Book orderBook : order.getBooks()) {
		    for (Book storeBook : store.getBooks()) {
			if ((orderBook.getAuthor()
				.equals(storeBook.getAuthor()))
				&& (orderBook.getName().equals(storeBook
					.getName()))
				&& (orderBook.getPrice() == storeBook
					.getPrice())
				&& (storeBook
					.isInStore())) {

			    store.deleteBook(storeBook.getId());
			    order.setStatus(false);
			    order.setDate(Calendar.getInstance());
			    break;
			}
		    }

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

    public void deleteOrder(Order order) {
	orders.remove(order);
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

    public ArrayList<Order> getExecutedOrders(int startYear, int startMonth,
	    int endYear, int endMonth) {
	ArrayList<Order> executed = new ArrayList<Order>();
	for (Order order : getExecutedOrders()) {
	    boolean condition1 = (order.getDate().get(Calendar.MONTH) + order
		    .getDate().get(Calendar.YEAR) * 12) > (startMonth + startYear * 12);
	    boolean condition2 = (order.getDate().get(Calendar.MONTH) + (order
		    .getDate().get(Calendar.YEAR) * 12)) < (endMonth + endYear * 12);
	    if ((condition1 == true) && (condition2 == true)) {
		executed.add(order);
	    }
	    ;
	}
	return executed;
    }

    public double getSumOfExecutedOrders(int startYear, int startMonth,
	    int endYear, int endMonth) {
	double sum = 0;

	for (Order order : getExecutedOrders()) {

	    boolean condition1 = (order.getDate().get(Calendar.MONTH) + order
		    .getDate().get(Calendar.YEAR) * 12) > (startMonth + startYear * 12);
	    boolean condition2 = (order.getDate().get(Calendar.MONTH) + (order
		    .getDate().get(Calendar.YEAR) * 12)) < (endMonth + endYear * 12);
	    if ((condition1 == true) && (condition2 == true))
		sum += order.getSum();
	}
	return sum;
    }

    public int getNumberOfExecutedOrders(int startYear, int startMonth,
	    int endYear, int endMonth) {

	return getExecutedOrders(startYear, startMonth, endYear, endMonth)
		.size();
    }

    public void CancelOrder(int id) {

	for (Order order : orders) {
	    if (order.getId() == id) {
		if (order.getStatus()) {
		    deleteOrder(order);
		    break;
		}
	    }
	    setOrders(orders);
	}
    }

    private void setOrders(ArrayList<Order> orders) {
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
