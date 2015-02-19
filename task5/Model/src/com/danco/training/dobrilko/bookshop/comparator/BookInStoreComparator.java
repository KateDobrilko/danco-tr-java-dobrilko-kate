package com.danco.training.dobrilko.bookshop.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.bookshop.model.Book;

public class BookInStoreComparator implements Comparator<Book> {

    public int compare(Book a, Book b) {

	return ((Boolean) a.isInStore()).compareTo(((Boolean) b.isInStore()));
    }

}