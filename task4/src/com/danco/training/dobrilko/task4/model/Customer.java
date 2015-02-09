package com.danco.training.dobrilko.task4.model;


public class Customer {
    private Order currentOrder;
    private int id;

    public Customer(int id) {
	this.id = id;
    }

    public void makeReply(Reply reply, Bookshop bookshop) {
	bookshop.getReplySystem().addReply(reply);
    }

    public void makeOrder(Order order, Bookshop bookshop) {
	bookshop.getOrderingSystem().addOrder(order);
	currentOrder = order;
    }

    public void cancelOrder(Order order, Bookshop bookshop) {
	if (bookshop.getOrderingSystem().getOrders().contains(order)) {
	    bookshop.getOrderingSystem().getOrders().remove(order);
	    currentOrder = null;
	}
    }

    public void takeOrder(Order order) {
	boolean fullyStaffed = false;
	for (BookType book : order.getBookOrder()) {
	    fullyStaffed = (book instanceof BookExemplar);
	}

	if (fullyStaffed) {
	    currentOrder = null;
	}
    }

    public Order getCurrentOrder() {
	return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
	this.currentOrder = currentOrder;
    }

    public int getId() {
	return id;
    }
    
    public String toString(){
	StringBuilder sb = new StringBuilder();
	sb.append(id);
	return sb.toString();
    }

}
