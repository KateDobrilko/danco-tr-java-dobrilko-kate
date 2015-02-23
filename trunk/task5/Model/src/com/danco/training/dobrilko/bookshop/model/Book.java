package com.danco.training.dobrilko.bookshop.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book {
	private String name;
	private String author;
	private double price;
	private int id;
	private boolean inStore;
	private Calendar dateOfAddition;
	private Calendar dateOfPublication;
	private boolean unclaimed;

	public Book() {

	}

	public Book(String name, String author, double price, Calendar dateOfPublication, int id) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.inStore = false;
		this.dateOfPublication = dateOfPublication;
		this.id = id;
		this.unclaimed = false;

	}

	public Book(String name, String author, double price, int id, int yearOfAddition, int monthOfAddition, int dayOfAddition, int yearOfPublication, int monthOfPublication, int dayOfPublication) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.inStore = true;
		this.id = id;
		GregorianCalendar da = new GregorianCalendar(yearOfAddition, monthOfAddition, dayOfAddition);
		GregorianCalendar dp = new GregorianCalendar(yearOfPublication, monthOfPublication, dayOfPublication);

		this.dateOfAddition = da;
		this.dateOfPublication = dp;
		if (yearOfAddition == (-1)) {
			this.unclaimed = false;

		} else {
			Calendar c = Calendar.getInstance();

			int currentMonth = c.get(Calendar.MONTH);
			int currentYear = c.get(Calendar.YEAR);

			int additionMonth = this.getDateOfAddition().get(Calendar.MONTH);
			int additionYear = this.getDateOfAddition().get(Calendar.YEAR);
			boolean condition = ((currentMonth + currentYear * 12) - (additionMonth + additionYear * 12)) > 6;

			if (condition) {
				this.unclaimed = true;
			} else {
				this.unclaimed = false;
			}
		}
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDateOfAddition() {
		return dateOfAddition;
	}

	public void setDateOfAddition(Calendar dateOfAddition) {
		this.dateOfAddition = dateOfAddition;
	}

	public Calendar getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Calendar dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public boolean isUnclaimed() {
		return unclaimed;
	}

	public void setUnclaimed(boolean unclaimed) {
		this.unclaimed = unclaimed;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name:");
		sb.append(name);
		sb.append("; ");
		sb.append("Author:");
		sb.append(author);
		sb.append("; ");
		sb.append("Price:");
		sb.append(Double.toString(price));
		sb.append("; ");
		sb.append("Id:");
		sb.append(Integer.toString(id));
		sb.append("; ");
		sb.append("Date Of Addition: ");
		if (dateOfAddition == null) {
			sb.append("not added; ");
		} else {
			sb.append(Integer.toString(dateOfAddition.get(Calendar.DATE)));
			sb.append("/");
			sb.append(Integer.toString(dateOfAddition.get(Calendar.MONTH)));
			sb.append("/");
			sb.append(Integer.toString(dateOfAddition.get(Calendar.YEAR)));

		}

		sb.append("Date Of Publication: ");
		sb.append(Integer.toString(dateOfPublication.get(Calendar.DATE)));
		sb.append("/");
		sb.append(Integer.toString(dateOfPublication.get(Calendar.MONTH)));
		sb.append("/");
		sb.append(Integer.toString(dateOfPublication.get(Calendar.YEAR)));

		return sb.toString();

	}
}
