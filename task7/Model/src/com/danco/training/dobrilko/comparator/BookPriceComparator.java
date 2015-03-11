package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entitiy.Book;

public class BookPriceComparator implements Comparator<Book> {

	public int compare(Book a, Book b) {

		return ((Double) a.getPrice()).compareTo(((Double) b.getPrice()));
	}

}
