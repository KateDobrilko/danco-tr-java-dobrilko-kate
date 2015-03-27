package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entity.Book;

public class BookPublicationDateComparator implements Comparator<Book> {

	public int compare(Book a, Book b) {

		return a.getDateOfPublication().compareTo(b.getDateOfPublication());
	}

}
