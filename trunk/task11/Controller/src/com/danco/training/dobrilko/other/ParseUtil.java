package com.danco.training.dobrilko.other;

import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.connection.ConnectionManager;
import com.danco.training.dobrilko.dao.GenericDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;
import com.danco.training.dobrilko.mysql.MySqlBookDao;

public class ParseUtil {

	private static Logger logger = Logger.getLogger(ParseUtil.class);

	public static Book parseBookString(String bookString) {
		String[] parsedString = bookString.split(";");
		Book book = null;

		if (parsedString.length == 10) {

			String name = parsedString[1];
			String author = parsedString[2];
			double price = Double.parseDouble(parsedString[3]);
			int id = Integer.parseInt(parsedString[0]);
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
			book = new Book(name, author, price, id, cur, pub, false, null);
		}

		return book;

	}

	public static Order parseOrderString(String orderString) {
		String[] strings = orderString.split(";");

		Integer id = Integer.parseInt(strings[0]);
		Double sum = Double.parseDouble(strings[1]);

		return new Order(id, null, sum);

	}

	public static Reply parseReplyString(String replyString) {

		String[] strings = replyString.split(";");

		int id = Integer.parseInt(strings[1]);
		int numberOfRequests = Integer.parseInt(strings[0]);
		boolean executed = strings[2].equals("true");
		Reply reply = null;
		GenericDao<Book, Integer> dao = new MySqlBookDao();
		try {
			reply = new Reply(numberOfRequests, id, executed, dao.getByPK(
					Integer.parseInt(strings[3]),
					ConnectionManager.getConnection()));
		} catch (NumberFormatException | PersistException e) {
			logger.error(e);
		}
		return reply;
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
		GenericDao<Book, Integer> dao;

		try {
			dao = new MySqlBookDao();

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
			for (Book book : dao
					.getAll("id", ConnectionManager.getConnection())) {
				if (book.getOrder().getId() == order.getId()) {
					sb.append(bookToString(book));
					sb.append(System.lineSeparator());
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}
		return sb.toString();
	}

	public static String replyToString(Reply reply) {
		StringBuilder sb = new StringBuilder();
		GenericDao<Book, Integer> dao;

		try {
			dao = new MySqlBookDao();
			sb.append("'");
			sb.append(Integer.toString(reply.getId()));
			sb.append("';");

			sb.append(bookToString(dao.getByPK(reply.getBook().getId(),
					ConnectionManager.getConnection())));

			sb.append(";'");
			sb.append(Integer.toString(reply.getNumberOfRequests()));
			sb.append("';'");
			if (reply.isExecuted()) {
				sb.append("executed'");
			} else {
				sb.append("not executed'");
			}
			sb.append(System.lineSeparator());
		} catch (PersistException e) {
			logger.error(e);
		}
		return sb.toString();
	}

}
