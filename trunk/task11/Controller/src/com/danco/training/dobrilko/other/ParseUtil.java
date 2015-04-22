package com.danco.training.dobrilko.other;

import java.util.ArrayList;
import java.util.Date;

import com.danco.training.dobrilko.bookshop.Bookshop;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;

public class ParseUtil {
	public static Book parseBookString(String bookString) {
		String[] parsedString = bookString.split(";");
		Book book = null;

		if (parsedString.length == 10) {
			String name = parsedString[0];
			String author = parsedString[1];
			double price = Double.parseDouble(parsedString[2]);
			int id = Integer.parseInt(parsedString[3]);
			int curYear = Integer.parseInt(parsedString[4]);
			int curMonth = Integer.parseInt(parsedString[5]);
			int curDay = Integer.parseInt(parsedString[6]);
			int pubYear = Integer.parseInt(parsedString[7]);
			int pubMonth = Integer.parseInt(parsedString[8]);
			int pubDay = Integer.parseInt(parsedString[9]);
			@SuppressWarnings("deprecation")
			Date cur = new Date(curYear, curMonth, curDay);
			@SuppressWarnings("deprecation")
			Date pub = new Date(pubYear, pubMonth, pubDay);
			book = new Book(name, author, price, id, cur, pub);
		}

		return book;

	}

	@SuppressWarnings("deprecation")
	public static Order parseOrderString(String orderString) {
		String[] strings = orderString.split(System.lineSeparator());
		String[] orderDescription;
		ArrayList<Book> books = new ArrayList<Book>();
		if (strings.length > 1) {
			orderDescription = strings[0].split(";");
			Date date;
			int id;
			int numberOfBooks;
			if (orderDescription.length < 4) {
				id = Integer.parseInt(orderDescription[0]);
				numberOfBooks = Integer.parseInt(orderDescription[1]);
				boolean status = orderDescription[2].equals("true");
				date = null;
				for (int i = 1; i <= numberOfBooks; i++) {
					int bookId = Integer.parseInt(strings[i].split(";")[0]);
					books.add(Bookshop.getInstance().getBookBase()
							.getById(bookId));
				}
				return new Order(id, books, status, date);
			}

			else {
				id = Integer.parseInt(orderDescription[0]);
				numberOfBooks = Integer.parseInt(orderDescription[1]);
				boolean status = orderDescription[2].equals("true");
				int year = Integer.parseInt(orderDescription[3]);
				int month = Integer.parseInt(orderDescription[4]);
				int day = Integer.parseInt(orderDescription[5]);
				date = new Date(year, month, day);
				for (int i = 1; i <= numberOfBooks; i++) {
					int bookId = Integer.parseInt(strings[i].split(";")[0]);
					books.add(Bookshop.getInstance().getBookBase()
							.getById(bookId));
				}
				return new Order(id, books, status, date);
			}

		} else {
			return null;
		}
	}

	public static Reply parseReplyString(String replyString) {
		String[] strings = replyString.split(System.lineSeparator());
		String[] replyDescription;
		if (strings.length > 1) {
			replyDescription = strings[0].split(";");
			int id = Integer.parseInt(replyDescription[0]);
			int numberOfRequests = Integer.parseInt(replyDescription[1]);
			boolean executed = replyDescription[2].equals("true");
			Book book = parseBookString(strings[1]);
			return new Reply(book, numberOfRequests, id, executed);
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static String bookToString(Book book) {
		StringBuilder sb = new StringBuilder();
		sb.append("'#");
		sb.append(Integer.toString(book.getId()));
		sb.append("';'");
		sb.append(book.getAuthor());
		sb.append("';'");
		sb.append(book.getName());
		sb.append("';'");
		if (book.isOrdered()) {
			sb.append("ordered");
		} else {
			sb.append("not ordered");
		}
		sb.append("';'");
		sb.append(Double.toString(book.getPrice()));
		sb.append("';'");
		if (book.getDateOfAddition() != null) {
			Date doa = book.getDateOfAddition();
			sb.append(Integer.toString(doa.getDate()));
			sb.append("/");
			sb.append(Integer.toString(doa.getMonth()));
			sb.append("/");
			sb.append(Integer.toString(doa.getYear()));
			sb.append("';'");
		}
		if (book.getDateOfPublication() != null) {
			Date dop = book.getDateOfPublication();
			sb.append(Integer.toString(dop.getDate()));
			sb.append("/");
			sb.append(Integer.toString(dop.getMonth()));
			sb.append("/");
			sb.append(Integer.toString(dop.getYear()));
			sb.append("'");
		}
		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	public static String orderToString(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(order.getId()));
		sb.append(";");
		if (order.getDateOfExecution() != null) {
			Date doe = order.getDateOfExecution();
			sb.append(Integer.toString(doe.getDate()));
			sb.append(";");
			sb.append(Integer.toString(doe.getMonth()));
			sb.append(";");
			sb.append(Integer.toString(doe.getYear()));
		}
		sb.append(System.lineSeparator());
		for (Book book : order.getBooks()) {
			sb.append(bookToString(book));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String replyToString(Reply reply) {
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(Integer.toString(reply.getId()));
		sb.append("';");
		sb.append(bookToString(reply.getBook()));
		sb.append(";'");
		sb.append(Integer.toString(reply.getNumberOfRequests()));
		sb.append("';'");
		if (reply.isExecuted()) {
			sb.append("executed'");
		} else {
			sb.append("not executed'");
		}
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
