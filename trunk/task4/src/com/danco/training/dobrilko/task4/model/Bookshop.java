package com.danco.training.dobrilko.task4.model;


public class Bookshop {
    private Store store;
    private Cashier cashier;
    private OrderingSystem orderingSystem;
    private ReplySystem replySystem;
    private CustomerBase customerBase;
    

    public Bookshop(Store store, Cashier cashier,
	    OrderingSystem orderingSystem, ReplySystem replySystem, CustomerBase customerBase) {
	this.store = store;
	this.cashier = cashier;
	this.orderingSystem = orderingSystem;
	this.replySystem = replySystem;
	this.customerBase = customerBase;
    }

    public Store getStore() {
	return store;
    }

    public Cashier getCashier() {
	return cashier;
    }

    public OrderingSystem getOrderingSystem() {
	return orderingSystem;
    }

    public ReplySystem getReplySystem() {
	return replySystem;
    }

    public CustomerBase getCustomerBase() {
	return customerBase;
    }
    
    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	sb.append(orderingSystem.toString()+System.lineSeparator());
	sb.append(replySystem.toString()+System.lineSeparator());
	sb.append(store.showExemplars()+System.lineSeparator());
	sb.append(cashier.toString()+System.lineSeparator());
	sb.append(customerBase.toString()+System.lineSeparator());
	return sb.toString();
	
    }

    

}
