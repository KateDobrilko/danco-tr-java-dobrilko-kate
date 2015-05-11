package com.danco.training.dobrilko.Controller.parser;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.entity.Reply;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.training.DAOFactory;
import com.danco.training.dobrilko.ModelDB.training.HibernateDAOFactory;
import com.danco.training.dobrilko.hibernateutil.HibernateUtil;

public class ParseUtil {
	private static Logger logger = Logger.getLogger(ParseUtil.class);

	public static Book parseBookString(String bookString) {
		String[] parsedString = bookString.split(";");
		Book book = null;

		String name = parsedString[1];
		String author = parsedString[2];
		double price = Double.parseDouble(parsedString[4]);
		int id = Integer.parseInt(parsedString[0]);
		int curYear = Integer.parseInt(parsedString[5]);
		int curMonth = Integer.parseInt(parsedString[6]);
		int curDay = Integer.parseInt(parsedString[7]);
		int pubYear = Integer.parseInt(parsedString[8]);
		int pubMonth = Integer.parseInt(parsedString[9]);
		int pubDay = Integer.parseInt(parsedString[10]);
		int numberOfExemplars = Integer.parseInt(parsedString[3]);
		@SuppressWarnings("deprecation")
		Date cur = new Date(curYear, curMonth, curDay);
		@SuppressWarnings("deprecation")
		Date pub = new Date(pubYear, pubMonth, pubDay);
		book = new Book(name, author, price, numberOfExemplars, id, pub, cur);

		return book;

	}

	public static Order parseOrderString(String orderString) {
		String[] strings = orderString.split(";");

		Integer id = Integer.parseInt(strings[0]);
		Double sum = Double.parseDouble(strings[1]);

		return new Order(null, null, id, sum);

	}

	@SuppressWarnings({ "unchecked" })
	public static Reply parseReplyString(String replyString) {

		String[] strings = replyString.split(";");

		int id = Integer.parseInt(strings[1]);
		int numberOfRequests = Integer.parseInt(strings[0]);
		boolean executed = strings[2].equals("true");
		Reply reply = null;
		DAOFactory factory = new HibernateDAOFactory();
		GenericDAO<Book, Integer> dao;
		HibernateUtil.buildSessionFactory();
		try {
			dao = (GenericDAO<Book, Integer>) factory.getDao(Book.class);
			HibernateUtil.openSessionAndBindToThread();

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			reply = new Reply(id, dao.getByPK(session,
					Integer.parseInt(strings[3])), numberOfRequests, executed);
		} catch (NumberFormatException | PersistException e) {
			logger.error(e);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		HibernateUtil.closeSessionFactory();
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
		sb.append(book.getId());
		sb.append("';'");
		sb.append("';'");
		sb.append(Double.toString(book.getPrice()));
		sb.append("';'");
		if (book.getArrivalDate() != null) {
			Date doa = book.getArrivalDate();
			sb.append(Integer.toString(doa.getDate()));
			sb.append("/");
			sb.append(Integer.toString(doa.getMonth()));
			sb.append("/");
			sb.append(Integer.toString(doa.getYear()));
			sb.append("';'");
		}
		if (book.getPublicationDate() != null) {
			Date dop = book.getPublicationDate();
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
		if (order.getExecutionDate() != null) {
			Date doe = order.getExecutionDate();
			sb.append(Integer.toString(doe.getDate()));
			sb.append(";");
			sb.append(Integer.toString(doe.getMonth()));
			sb.append(";");
			sb.append(Integer.toString(doe.getYear()));
			sb.append(order.getSum());
		}
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	public static String replyToString(Reply reply) {
		StringBuilder sb = new StringBuilder();

		sb.append("'");
		sb.append(Integer.toString(reply.getId()));
		sb.append("';");

		sb.append(";'");
		sb.append(Integer.toString(reply.getNumber()));
		sb.append("';'");
		if (reply.getExecuted()) {
			sb.append("executed'");
		} else {
			sb.append("not executed'");
		}
		sb.append(System.lineSeparator());
		return sb.toString();
	}
}
