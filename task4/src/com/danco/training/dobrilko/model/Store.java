package com.danco.training.dobrilko.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.danco.training.dobrilko.comparator.BookInStoreComparator;
import com.danco.training.dobrilko.comparator.BookNameComparator;
import com.danco.training.dobrilko.comparator.BookPriceComparator;
import com.danco.training.dobrilko.comparator.BookPublicationDateComparator;

public class Store {
    private Map<BookType, ArrayList<BookExemplar>> books = new HashMap<BookType, ArrayList<BookExemplar>>();

    public Store() {

    }

    public Store(Map<BookType, ArrayList<BookExemplar>> books) {
	this.books = books;
    }

    public BookType[] sortByName(BookType[] array) {
	Arrays.sort(array, new BookNameComparator());
	return array;
    }

    public ArrayList<BookExemplar> sortByPublicationDate(ArrayList<BookExemplar> array) {
	BookExemplar[] btArray=new BookExemplar[array.size()];
        array.toArray(btArray);
	Arrays.sort(btArray, new BookPublicationDateComparator());
	return array;
    }

    public BookType[] sortByPrice(BookType[] array) {
	Arrays.sort(array, new BookPriceComparator());
	return array;
    }

    public BookType[] sortByInStore(BookType[] array) {
	Arrays.sort(array, new BookInStoreComparator());
	return array;
    }

    public BookType[] getBookTypes() {
	BookType[] bt = new BookType[books.keySet().size()];
	for (int i = 0; i < books.keySet().size(); i++) {
	    bt[i] = (BookType) books.keySet().toArray()[i];
	}
	return bt;
    }

    public ArrayList<BookExemplar> getBookExemplars() {
	ArrayList<BookExemplar> exemplars = new ArrayList<BookExemplar>();
	for (ArrayList<BookExemplar> bookList : books.values()) {
	    if(bookList!=null)
	    {for (BookExemplar book : bookList) {
		exemplars.add(book);
	    }}
	}
	return exemplars;
    }

    public void setBooks(Map<BookType, ArrayList<BookExemplar>> books) {
	this.books = books;
    }

    public ArrayList<BookExemplar> getUnclaimedBooks() {
	ArrayList<BookExemplar> unclaimedBooks = new ArrayList<BookExemplar>();
	for (ArrayList<BookExemplar> bookList : books.values()) {
	    for (BookExemplar book : bookList) {
		if (book.isUnclaimed()) {
		    unclaimedBooks.add(book);
		}
	    }
	}
	return unclaimedBooks;
    }

    public void deleteBookType(BookType bookType) {

	books.remove(bookType);

    }

    public void addBookType(BookType book) {

	if (books.containsKey(book)) {
	} else {
	    books.put(book, null);
	}
    }

    public void deleteBookExemplar(BookExemplar bookExemplar) {

	for (BookType book : books.keySet()) {
	    if ((book.getName().equals(bookExemplar.getName()))
		    && (book.getAuthor().equals(bookExemplar.getAuthor()))
		    && (book.getPrice() == bookExemplar.getPrice())) {
		books.remove(book, bookExemplar);
		if (books.get(book).equals(null)) {
		    BookType b = book;
		    books.remove(book);
		    b.setInStore(false);
		    books.put(b, null);

		}
	    }
	}

    }

    public void addBookExemplar(BookExemplar bookExemplar) {

	BookType bt = new BookType();
	ArrayList<BookExemplar> be;
	for (BookType book : books.keySet()) {
	    if (book.getName() != null) {
		if ((book.getName().equals(bookExemplar.getName()))
			&& (book.getAuthor().equals(bookExemplar.getAuthor()))
			&& (book.getPrice() == bookExemplar.getPrice())) {
		    bt = book;
		}
		if (book.equals(null)) {
		    this.addBookType(new BookType(bookExemplar.getName(),
			    bookExemplar.getAuthor(), bookExemplar.getPrice()));
		}
	    }
	}
	be = books.get(bt);
	books.remove(bt);
	bt.setInStore(true);
	bookExemplar.setDateOfAddition(Calendar.getInstance());
	if (be != null) {
	} else {
	    be = new ArrayList<BookExemplar>();
	    be.add(bookExemplar);
	}
	books.put(bt, be);
    }

    public String showExemplars() {
	ArrayList<BookExemplar> array = this.sortByPublicationDate(getBookExemplars());
	StringBuilder sb = new StringBuilder();
	sb.append("Store " + System.lineSeparator());
	sb.append(array.size() + System.lineSeparator());
	for (BookExemplar bookExemplar : array) {
	    sb.append(bookExemplar.toString() + System.lineSeparator());
	}
	return sb.toString();
    }

    public String showTypes() {
	StringBuilder sb = new StringBuilder();
	sb.append("Store " + System.lineSeparator());
	sb.append(getBookExemplars().size() + System.lineSeparator());
	for (BookType bookType : getBookTypes()) {
	    if (books.get(bookType).equals(null)) {
		sb.append(bookType.toString() + System.lineSeparator());
	    }

	}

	return sb.toString();
    }

    public String showUnclaimedBooks() {
	StringBuilder sb = new StringBuilder();
	sb.append("Store Unclaimed " + System.lineSeparator());
	sb.append(getUnclaimedBooks().size() + System.lineSeparator());
	for (BookExemplar bookExemplar : getUnclaimedBooks()) {
	    sb.append(bookExemplar.toString() + System.lineSeparator());
	}
	return sb.toString();
    }

}
