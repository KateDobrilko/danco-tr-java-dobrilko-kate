package com.danco.training.dobrilko.model;

public class BookType {

    private String name;
    private String author;
    private double price;
    private boolean inStore;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public boolean isInStore() {
	return inStore;
    }

    public void setInStore(boolean inStore) {
	this.inStore = inStore;
    }

    public BookType() {

    }

    public BookType(String name, String author, double price) {

	this.name = name;
	this.author = author;
	this.price = price;
	this.inStore = false;

    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("BookType|");
	sb.append(name);
	sb.append("|");
	sb.append(author);
	sb.append("|");
	sb.append(Double.toString(price));
	return sb.toString();
    }

}
