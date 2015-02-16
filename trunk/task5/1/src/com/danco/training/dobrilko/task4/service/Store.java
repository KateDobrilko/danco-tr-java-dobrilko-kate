package com.danco.training.dobrilko.task4.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import com.danco.training.dobrilko.task4.model.Book;
import com.danco.training.dobrilko.task4.model.Reply;
import com.danco.training.dobrilko.task4.comparator.BookNameComparator;
import com.danco.training.dobrilko.task4.comparator.BookInStoreComparator;
import com.danco.training.dobrilko.task4.comparator.BookPriceComparator;
import com.danco.training.dobrilko.task4.comparator.BookPublicationDateComparator;

public class Store {
    private ArrayList<Book> books = new ArrayList<Book>();

    public void sortByName() {

	Collections.sort(books, new BookNameComparator());

    }

    public void sortByInStore() {

	Collections.sort(books, new BookInStoreComparator());

    }

    public void sortByPrice() {

	Collections.sort(books, new BookPriceComparator());

    }

    public void sortByPublicationDate() {

	Collections.sort(books, new BookPublicationDateComparator());

    }

    public ArrayList<Book> getBooks() {
	return books;
    }

    public void setBooks(ArrayList<Book> books, ReplySystem replyS) {
	if (!replyS.equals(null)) {
	    books.forEach((Book book) -> addBook(book, replyS));
	}
    }

    public void deleteBook(Book book) {
	
      if(books.contains(book))
      {
	books.removeIf((Book b) -> b.equals(book));
      }
      else{
	  throw new RuntimeException("Book not found!");
      }

    }

    public void addBook(Book book, ReplySystem replySystem) {
	if (!replySystem.equals(null)) {
	    if (book.isInStore() == false) {
		if (books.contains(book) == false) {
		    books.add(book);
		}
	    } else {
		books.add(book);
	    }
	    for (Reply reply : replySystem.getReplies()) {

		boolean condition = (reply.getBook().getAuthor().equals(book
			.getAuthor()))
			&& ((reply.getBook().getName().equals(book.getName())))
			&& ((reply.getBook().getPrice() == book.getPrice()))
			&& ((reply.getBook().getDateOfPublication()
				.get(Calendar.YEAR)) == (book
				.getDateOfPublication().get(Calendar.YEAR)))
			&& ((reply.getBook().getDateOfPublication()
				.get(Calendar.MONTH)) == (book
				.getDateOfPublication().get(Calendar.MONTH)))
			&& ((reply.getBook().getDateOfPublication()
				.get(Calendar.DATE)) == (book
				.getDateOfPublication().get(Calendar.DATE)))
			&& (book.isInStore());
		if (condition) {

		    reply.setExecuted(true);
		    ;

		}
	    }
	}
    }

    public ArrayList<Book> getUnclaimedBooks() {
	ArrayList<Book> unclaimedBooks = new ArrayList<Book>();
	for (Book book : books) {
	    if (book.isUnclaimed()) {
		unclaimedBooks.add(book);
	    }
	}
	return unclaimedBooks;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(Integer.toString(books.size()));
	sb.append(System.lineSeparator());
	books.forEach((Book book) -> sb.append(book.toString()
		+ System.lineSeparator()));
	return sb.toString();
    }
}
