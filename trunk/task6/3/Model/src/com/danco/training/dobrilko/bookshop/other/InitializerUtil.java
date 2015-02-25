package com.danco.training.dobrilko.bookshop.other;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.service.BookShop;

public class InitializerUtil {
	

	public static void Initialize(BookShop bs) {
		ArrayList<Book> books = new ArrayList<Book>();
		ArrayList<Order> orders = new ArrayList<Order>();
		ArrayList<Reply> replies = new ArrayList<Reply>();
		books.add(new Book("Ulysses", "James Joyce", 13.5, new GregorianCalendar(21, 8, 2011), 651654));
		books.add(new Book("Lolita", "Vladimir Nabokov", 16.12, new GregorianCalendar(2, 9, 2010), 365464));
		orders.add(new Order(9875156, books, 27, 10, 2013));
		books.clear();
		books.add(new Book("Beloved", "Toni Morrison", 2.4, new GregorianCalendar(21, 9, 2011), 656152));
		orders.add(new Order(5451, books));
		books.clear();
		books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 13.2, new GregorianCalendar(21, 8, 2011), 165165));
		books.add(new Book("The Grapes of Wrath", "John Steinbeck", 15.0, new GregorianCalendar(21, 8, 2011), 661656));
		books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 11.2, new GregorianCalendar(20, 7, 2010), 165167));
		orders.add(new Order(98475156, books, 26, 10, 2013));
		books.clear();
		bs.getOrderingSystem().setOrders(orders);
		Book book = new Book("Lolita", "Vladimir Nabokov", 16.12, new GregorianCalendar(2, 9, 2010), 365464);
		replies.add(new Reply(book, 3, 56465165));
		book = new Book("To Kill a Mockingbird", "by Harper Lee", 7.8, new GregorianCalendar(7, 10, 2012), 35421);
		replies.add(new Reply(book, 1, 577854));
		book = new Book("The Lord of the Rings", "J. R. R. Tolkien", 10.5, new GregorianCalendar(10, 11, 2014), 54612);
		replies.add(new Reply(book, 5, 12357));
		bs.getReplySystem().setReplies(replies);
		books.clear();
		book = new Book("Catch-22", "Joseph Heller", 13.5, 6545, 2010, 11, 12, 2012, 8, 21);
		books.add(book);
		book = new Book("The Catcher in the Rye", "J. D. Salinger", 3.5, new GregorianCalendar(21, 8, 2010), 651654);
		books.add(book);
		book = new Book("The Sound and the Fury", "William Faulkner", 8.5, new GregorianCalendar(21, 8, 2011), 2656451);
		bs.getStore().addBook(book, bs.getReplySystem());
		book = new Book("Beloved", "Toni Morrison", 2.4, 656152, 2011, 8, 21, 2011, 9, 21);
		books.add(book);
		bs.getStore().setBooks(books, bs.getReplySystem());

	}
}
