package com.danco.training.dobrilko.task4.model;
import java.util.Calendar;

public class BookExemplar extends BookType {
    private int id;
    private Calendar dateOfAddition;
    private Calendar dateOfPublication;
    private boolean unclaimed;

    public BookExemplar() {

    }

    public BookExemplar(int id, Calendar dateOfAddition,
	    Calendar dateOfPublication, String name, String author, double price) {
	super(name, author, price);
	this.id = id;
	this.dateOfAddition = dateOfAddition;
	this.dateOfPublication = dateOfPublication;
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

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("BookExemplar|");
	sb.append(id);
	sb.append("|");
	sb.append(getName());
	sb.append("|");
	sb.append(getAuthor());
	sb.append("|");
	
	sb.append(Integer.toString(dateOfAddition.get(Calendar.YEAR)) + "/"
		+ Integer.toString(dateOfAddition.get(Calendar.MONTH)) + "/"
		+ Integer.toString(dateOfAddition.get(Calendar.DATE)));
	sb.append("|");
	
	sb.append(Integer.toString(dateOfPublication.get(Calendar.YEAR)) + "/"
		+ Integer.toString(dateOfPublication.get(Calendar.MONTH)) + "/"
		+ Integer.toString(dateOfPublication.get(Calendar.DATE)));
	sb.append("|");
	
	sb.append(Double.toString(getPrice()));
	
	return sb.toString();
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

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public boolean isUnclaimed() {
	return unclaimed;
    }

    public void setUnclaimed(boolean unclaimed) {
	this.unclaimed = unclaimed;
    }
}
