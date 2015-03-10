package com.danco.training.dobrilko.ioutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entitiy.Book;
import com.danco.training.dobrilko.entitiy.Order;
import com.danco.training.dobrilko.entitiy.Reply;

public class SpecificIOFeaturesUtil {
	@SuppressWarnings("deprecation")
	public static Book readBook() throws InputMismatchException{
		IOUtil.print("Name:");
		String name = IOUtil.readString();
		IOUtil.print("Author:");
		String author = IOUtil.readString();
		IOUtil.print("Price:");
		double price = IOUtil.readDouble();
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		Date dop = SpecificIOFeaturesUtil.readDate();
		Date cur = new Date();
		cur.setMonth(cur.getMonth()+1);
		cur.setYear(cur.getYear()+1900);
		return new Book(name, author, price, id, cur, dop);

	}

	public static Order readOrder() throws InputMismatchException{
		ArrayList<Book> books = new ArrayList<Book>();
		IOUtil.print("Order id:");
		int id = IOUtil.readInt();
		IOUtil.print("Number of books:");
		int num = IOUtil.readInt();

		IOUtil.print("Executed?:");
		boolean status = IOUtil.readBoolean();

		if (status == true) {
			for (int i = 0; i < num; i++) {

				books.add(readBook());
			}
			Date date = readDate();
			return new Order(id, books, status, date);
		} else {
			for (int i = 0; i < num; i++) {
				IOUtil.print("Book #" + i + " id:");
				books.add(BookshopController.getBookById(IOUtil.readInt()));
			}
			return new Order(id, books, status, null);
		}
	}

	public static Reply readReply() throws InputMismatchException{
		Book book = readBook();
		IOUtil.print("Number of replies:");
		int number = IOUtil.readInt();
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		return new Reply(book, number, id, false);
	}

	@SuppressWarnings("deprecation")
	public static Date readDate() throws InputMismatchException{
		IOUtil.print("Input date (y,m,d):");

		return new Date(IOUtil.readInt(), IOUtil.readInt(), IOUtil.readInt());
	}

	@SuppressWarnings("deprecation")
	public static void printBook(Book book) {
		IOUtil.print("'#");
		IOUtil.print(Integer.toString(book.getId()));
		IOUtil.print("';'");
		IOUtil.print(book.getAuthor());
		IOUtil.print("';'");
		IOUtil.print(book.getName());
		IOUtil.print("';'");
		if (book.isOrdered()) {
			IOUtil.print("ordered");
		} else {
			IOUtil.print("not ordered");
		}
		IOUtil.print("';'");
		IOUtil.print(Double.toString(book.getPrice()));
		IOUtil.print("';'");
		if (book.getDateOfAddition() != null) {
			Date doa = book.getDateOfAddition();
			IOUtil.print(Integer.toString(doa.getDate()));
			IOUtil.print("/");
			IOUtil.print(Integer.toString(doa.getMonth()));
			IOUtil.print("/");
			IOUtil.print(Integer.toString(doa.getYear()));
			IOUtil.print("';'");
		}
		if (book.getDateOfPublication() != null) {
			Date dop = book.getDateOfPublication();
			IOUtil.print(Integer.toString(dop.getDate()));
			IOUtil.print("/");
			IOUtil.print(Integer.toString(dop.getMonth()));
			IOUtil.print("/");
			IOUtil.print(Integer.toString(dop.getYear()));
			IOUtil.print("'");
		}

	}

	public static void printReply(Reply reply) {
		IOUtil.print("'");
		IOUtil.print(Integer.toString(reply.getId()));
		IOUtil.print("';");
		printBook(reply.getBook());
		IOUtil.print(";'");
		IOUtil.print(Integer.toString(reply.getNumberOfRequests()));
		IOUtil.print("';'");
		if (reply.isExecuted()) {
			IOUtil.print("executed");
		} else {
			IOUtil.print("not executed");
		}
		IOUtil.println();
	}

	@SuppressWarnings("deprecation")
	public static void printOrder(Order order) {
		IOUtil.print(Integer.toString(order.getId()));
		IOUtil.print(";");
		if (order.getDateOfExecution() != null) {
			Date doe = order.getDateOfExecution();
			IOUtil.print(Integer.toString(doe.getDate()));
			IOUtil.print(";");
			IOUtil.print(Integer.toString(doe.getMonth() + 1));
			IOUtil.print(";");
			IOUtil.print(Integer.toString(doe.getYear() + 1900));
		}
		IOUtil.println();
		for (Book book : order.getBooks()) {
			printBook(book);
			IOUtil.println();
		}
	}

	public static int readId() throws InputMismatchException{
		IOUtil.print("Id:");
		return IOUtil.readInt();
	}
}
