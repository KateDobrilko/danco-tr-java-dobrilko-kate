package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entitiy.Book;

public class BookNameComparator implements Comparator<Book> {

	public int compare(Book a, Book b) {

		return a.getName().compareTo(b.getName());
	}

}
