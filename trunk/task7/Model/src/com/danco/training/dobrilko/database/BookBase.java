package com.danco.training.dobrilko.database;

import java.io.Serializable;
import java.util.ArrayList;

import com.danco.training.dobrilko.entity.Book;

public class BookBase implements Serializable {

	private static final long serialVersionUID = -350545889584292494L;
	
	private ArrayList<Book> books;
	
	
	public BookBase() {
		this.setBooks(new ArrayList<Book>());
	}

	public BookBase(ArrayList<Book> books) {
		this.setBooks(books);
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public boolean add(Book book) {

		boolean idUnique = true;
		for (Book b : this.books) {
			if (b.getId() == book.getId()) {
				idUnique = false;
				b.setNumberOfExemplars(b.getNumberOfExemplars()+1);;
				break;
			}
		}
		if (idUnique) {
			this.books.add(book);
		}

		return idUnique;
	}

	public boolean delete(int id) {
		boolean successfullyDeleted = false;
		boolean idUnique = false;

		for (Book b : this.books) {
			if (b.getId() == id) {
				idUnique = true;
				break;
			}
		}
		if (idUnique) {
			Book book = getById(id);
			if ((!book.isOrdered())&&(book.getNumberOfExemplars()!=0)) {
				book.setNumberOfExemplars(book.getNumberOfExemplars()-1);
				book.setDateOfAddition(null);
				successfullyDeleted = true;
			}

		}

		return successfullyDeleted;

	}

	public boolean update(int id, Book book) {
		boolean idMatches = false;
		for (Book b : this.books) {
			if (b.getId() == id) {
				idMatches = true;
				b = book;
				break;
			}
		}
		return idMatches;
	}

	public Book getById(int id) {
		Book book = new Book();
		for (Book b : this.books) {
			if (b.getId() == id) {
				book = b;
				break;
			}
		}
		return book;
	}

	public Book[] getBooksArray() {
		Book [] booksArray = new Book[books.size()];
		booksArray = books.toArray(booksArray);
		return booksArray;
	}
}
