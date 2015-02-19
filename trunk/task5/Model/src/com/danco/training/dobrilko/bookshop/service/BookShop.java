package com.danco.training.dobrilko.bookshop.service;

public class BookShop {
    private Store store = new Store();
    private OrderingSystem orderingSystem = new OrderingSystem();
    private ReplySystem replySystem = new ReplySystem();

    public BookShop(){
    
    }
    public Store getStore() {
	return store;
    }

    public void setStore(Store store) {
	this.store = store;
    }

    public OrderingSystem getOrderingSystem() {
	return orderingSystem;
    }

    public void setOrderingSystem(OrderingSystem orderingSystem) {
	this.orderingSystem = orderingSystem;
    }

    public ReplySystem getReplySystem() {
	return replySystem;
    }

    public void setReplySystem(ReplySystem replySystem) {
	this.replySystem = replySystem;
    }

    public BookShop(Store store, OrderingSystem orderingSystem,
	    ReplySystem replySystem) {
	this.store = store;
	this.orderingSystem = orderingSystem;
	this.replySystem = replySystem;

    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(this.orderingSystem.toString() + System.lineSeparator()
		+ System.lineSeparator());
	sb.append(this.replySystem.toString() + System.lineSeparator()
		+ System.lineSeparator());
	sb.append(this.store.toString() + System.lineSeparator()
		+ System.lineSeparator());
	return sb.toString();
    }

}
