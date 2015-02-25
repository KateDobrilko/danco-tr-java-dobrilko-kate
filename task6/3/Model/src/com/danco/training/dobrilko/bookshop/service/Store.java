package com.danco.training.dobrilko.bookshop.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.bookshop.comparator.BookInStoreComparator;
import com.danco.training.dobrilko.bookshop.comparator.BookNameComparator;
import com.danco.training.dobrilko.bookshop.comparator.BookPriceComparator;
import com.danco.training.dobrilko.bookshop.comparator.BookPublicationDateComparator;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.propertiesutil.PropertiesUtil;

public class Store implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2832170485920726142L;
	
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

	public void deleteBook(int id) {
		Book book = null;
		boolean condition = false;
		for (Book b : books) {
			if (b.getId() == id) {
				condition = true;
				book = b;
				break;
			}
		}
		if (condition) {
			books.removeIf((Book b) -> b.getId() == id);
		}
		if (book == null) {
			Logger logger = Logger.getLogger(Store.class);
			logger.warn("Book with id:" + Integer.toString(id) + " doesn't exists.");
		}
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public void addBook(Book book, ReplySystem replySystem) {
		if (!replySystem.equals(null)) {
			if (book.isInStore() == false) {
				if (books.contains(book) == false) {
					books.add(book);
				}
			} else {
				books.add(book);

				if (PropertiesUtil.getMarkRepliesAsExecuted()) {
					for (Reply reply : replySystem.getReplies()) {

						boolean condition = (reply.getBook().getAuthor().equals(book.getAuthor())) && ((reply.getBook().getName().equals(book.getName()))) && ((reply.getBook().getPrice() == book.getPrice())) && (book.isInStore());
						if (condition) {
							Calendar date = reply.getBook().getDateOfAddition();
							date.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
							reply.getBook().setDateOfAddition(date);
							reply.setExecuted(true);
							;

						}
					}
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
		books.forEach((Book book) -> sb.append(book.toString() + System.lineSeparator()));
		return sb.toString();
	}
}
