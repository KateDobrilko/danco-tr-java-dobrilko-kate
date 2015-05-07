package com.danco.dobrilko.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.dobrilko.api.IBookshopController;
import com.danco.dobrilko.connector.ConnectionManager;
import com.danco.dobrilko.dao.MySqlOrderDAO;
import com.danco.dobrilko.entity.Book;
import com.danco.dobrilko.entity.Order;
import com.danco.dobrilko.entity.Reply;
import com.danco.dobrilko.genericdao.GenericDAO;
import com.danco.dobrilko.parser.ParseUtil;
import com.danco.dobrilko.persistexception.PersistException;
import com.danco.dobrilko.property.PropertyStorage;
import com.danco.dobrilko.training.DAOFactory;
import com.danco.dobrilko.training.MySQLDAOFactory;

public class BookshopController implements IBookshopController {

	private Logger logger = Logger.getLogger(BookshopController.class);
	@SuppressWarnings("rawtypes")
	private DAOFactory factory = new MySQLDAOFactory();

	@SuppressWarnings("unchecked")
	public synchronized void addOrder(String orderString) {

		try {
			GenericDAO<Order, Integer> dao = factory.getDao(Order.class);
			dao.persist(ParseUtil.parseOrderString(orderString),
					ConnectionManager.getConnection());
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void cancelOrder(Integer id) {

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			MySqlOrderDAO daoOrder = (MySqlOrderDAO) factory
					.getDao(Order.class);
			GenericDAO<Book, Integer> daoBook = factory.getDao(Book.class);

			Order order = daoOrder.getByPK(id, connection);
			if (order.getExecuted()) {
				for (Book book : daoOrder.getRelatedBooks(connection, order)) {
					book.setNumberOfExemplars(book.getNumberOfExemplars() + 1);
					daoBook.update(book, connection);
				}
				daoOrder.delete(daoOrder.getByPK(id, connection), connection);
				connection.commit();

			} else {
				connection.rollback();
				Logger logger = Logger.getLogger(BookshopController.class);
				logger.warn("Order with id:" + Integer.toString(id)
						+ " is already executed.");

			}
		} catch (PersistException | SQLException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void executeOrder(Integer id) {

		MySqlOrderDAO daoOrder;
		Connection connection = ConnectionManager.getConnection();
		try {
			daoOrder = (MySqlOrderDAO) factory.getDao(Order.class);

			GenericDAO<Book, Integer> daoBook = factory.getDao(Book.class);

			connection.setAutoCommit(false);

			Order order = daoOrder.getByPK(id, connection);

			for (Book b : daoOrder.getRelatedBooks(connection, order)) {

				order.setSum(order.getSum() + b.getPrice());
				b.setNumberOfExemplars(b.getNumberOfExemplars() - 1);
				daoBook.update(b, connection);
			}

			order.setExecuted(true);
			order.setExecutionDate(new Date());
			daoOrder.update(order, connection);
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String getOrdersString() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			GenericDAO<Order, Integer> daoOrder = factory.getDao(Order.class);

			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("id", connection)) {
				sb.append(ParseUtil.orderToString(order));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	public double getSumOfExecutedOrders(Date startDate, Date endDate) {
		double sum = 0;

		for (Order order : getExecutedOrders(startDate, endDate)) {

			sum += order.getSum();
		}
		return sum;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Order> getExecutedOrders() {

		ArrayList<Order> executed = new ArrayList<Order>();

		GenericDAO<Order, Integer> daoOrder;
		Connection connection = ConnectionManager.getConnection();
		try {
			daoOrder = (MySqlOrderDAO) factory.getDao(Order.class);
			;

			for (Order order : daoOrder.getAll("id", connection)) {
				if (order.getExecuted()) {
					executed.add(order);
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}
		return executed;
	}

	private ArrayList<Order> getExecutedOrders(Date startDate, Date endDate) {
		ArrayList<Order> executed = new ArrayList<Order>();
		for (Order order : getExecutedOrders()) {
			boolean condition1 = order.getExecutionDate().after(startDate);
			boolean condition2 = order.getExecutionDate().before(endDate);
			if ((condition1 == true) && (condition2 == true)) {
				executed.add(order);
			}
			;
		}
		return executed;
	}

	public String getExecutedOrdersString(Date startDate, Date endDate) {
		StringBuilder sb = new StringBuilder();
		for (Order order : getExecutedOrders()) {
			boolean condition1 = order.getExecutionDate().after(startDate);
			boolean condition2 = order.getExecutionDate().before(endDate);
			if ((condition1 == true) && (condition2 == true)) {
				sb.append(ParseUtil.orderToString(order));
				sb.append(System.lineSeparator());
			}
			;
		}
		return sb.toString();
	}

	public Integer getNumberOfExecutedOrders(Date startDate, Date endDate) {
		return getExecutedOrders(startDate, endDate).size();
	}

	public String sortOrdersByDate() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			@SuppressWarnings("unchecked")
			GenericDAO<Order, Integer> daoOrder = (MySqlOrderDAO) factory
					.getDao(Order.class);
			;

			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("dateOfExecution", connection)) {
				sb.append(ParseUtil.orderToString(order));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();

	}

	@SuppressWarnings("unchecked")
	public String sortOrdersByExecution() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			GenericDAO<Order, Integer> daoOrder = (MySqlOrderDAO) factory
					.getDao(Order.class);

			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("status", connection)) {
				sb.append(ParseUtil.orderToString(order));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void markUnclaimedBooks(Connection connection) {
		GenericDAO<Book, Integer> dao;
		try {
			dao = factory.getDao(Book.class);

			for (Book book : dao.getAll("id", connection)) {
				if (book.getArrivalDate() != null) {
					Date date = new Date();

					LocalDate ldcurr = LocalDate.of(date.getYear() + 1900,
							date.getMonth() + 1, date.getDate());
					LocalDate ld = LocalDate.of(
							book.getArrivalDate().getYear(), book
									.getArrivalDate().getMonth(), book
									.getArrivalDate().getDate());
					Period period = Period.between(ld, ldcurr);

					if (period.getMonths() >= PropertyStorage.getInstance()
							.getMonthsToMarkUnclaimed()) {
						book.setUncalimed(true);
					}

					dao.update(book, connection);
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String sortOrdersByPrice() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			GenericDAO<Order, Integer> daoOrder = factory.getDao(Order.class);

			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("sum", connection)) {
				sb.append(ParseUtil.orderToString(order));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();

	}

	@SuppressWarnings("unchecked")
	public String getOrderStringById(Integer id) {
		GenericDAO<Order, Integer> dao;
		Connection connection = ConnectionManager.getConnection();
		String out = null;
		try {

			dao = factory.getDao(Order.class);
			out = ParseUtil.orderToString(dao.getByPK(id, connection));
		} catch (PersistException e) {
			logger.error(e);
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	public synchronized void addBook(String bookString) {

		GenericDAO<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			dao = factory.getDao(Book.class);

			if (PropertyStorage.getInstance().getMarkRepliesAsExecuted()) {
				dao.persist(ParseUtil.parseBookString(bookString), connection);
				markRepliesAsExecuted(connection);
			}

			else {
				dao.persist(ParseUtil.parseBookString(bookString), connection);
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void deleteBook(Integer id) {

		GenericDAO<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();
		try {
			connection.setAutoCommit(false);
			dao = factory.getDao(Book.class);

			dao.delete(dao.getByPK(id, connection), connection);
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String getBooks() {
		StringBuilder sb = new StringBuilder();

		GenericDAO<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();

		try {
			dao = factory.getDao(Book.class);
			for (Book book : dao.getAll("id", connection)) {
				sb.append(ParseUtil.bookToString(book));
				sb.append(System.lineSeparator());
			}
		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String getUnclaimedBooks() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();

		GenericDAO<Book, Integer> dao;
		try {
			connection.setAutoCommit(false);
			markUnclaimedBooks(connection);
			dao = factory.getDao(Book.class);

			for (Book book : dao.getAll("id", connection)) {
				if (book.getUncalimed()) {
					sb.append(ParseUtil.bookToString(book));
					sb.append(System.lineSeparator());

				}
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e1);
			}
			logger.error(e);
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String sortBookByPublicationDate() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();

		try {
			GenericDAO<Book, Integer> daoBook = factory.getDao(Book.class);

			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("PublicationDate", connection)) {
				sb.append(ParseUtil.bookToString(book));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String sortBookByName() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			GenericDAO<Book, Integer> daoBook = factory.getDao(Book.class);

			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("name", connection)) {
				sb.append(ParseUtil.bookToString(book));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String sortBookByPrice() {
		Connection connection = ConnectionManager.getConnection();
		StringBuilder sb = new StringBuilder();
		try {
			GenericDAO<Book, Integer> daoBook = factory.getDao(Book.class);

			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("price", connection)) {
				sb.append(ParseUtil.bookToString(book));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String getBookById(Integer id) {
		String bookString = null;

		GenericDAO<Book, Integer> dao;

		try {
			dao = factory.getDao(Book.class);

			bookString = ParseUtil.bookToString(dao.getByPK(id,
					ConnectionManager.getConnection()));
		} catch (PersistException e) {
			logger.error(e);
		}

		return bookString;
	}

	@SuppressWarnings("unchecked")
	public String getReplies() {
		StringBuilder sb = new StringBuilder();

		GenericDAO<Reply, Integer> dao;

		try {
			dao = factory.getDao(Reply.class);
			for (Reply reply : dao.getAll("id",
					ConnectionManager.getConnection())) {
				sb.append(ParseUtil.replyToString(reply));
				sb.append(System.lineSeparator());
			}

		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	public void sortRepliesByAlphabet() {

	}

	@SuppressWarnings("unchecked")
	public String sortRepliesByNumber() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();
		try {
			GenericDAO<Reply, Integer> daoReply = factory.getDao(Reply.class);

			connection.setAutoCommit(false);
			for (Reply reply : daoReply.getAll("numberOfRequests", connection)) {
				sb.append(ParseUtil.replyToString(reply));
				sb.append(System.lineSeparator());
			}
			connection.commit();
		} catch (PersistException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public synchronized void addReply(String replyString) {

		GenericDAO<Reply, Integer> dao;

		try {
			dao = factory.getDao(Reply.class);
			dao.persist(ParseUtil.parseReplyString(replyString),
					ConnectionManager.getConnection());

		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public void markRepliesAsExecuted(Connection connection) {
		GenericDAO<Book, Integer> daoBook;
		GenericDAO<Reply, Integer> daoReply;

		try {
			daoReply = factory.getDao(Reply.class);

			daoBook = factory.getDao(Book.class);
			for (Reply reply : daoReply.getAll("id", connection)) {
				for (Book book : daoBook.getAll("id", connection)) {

					Book book1 = daoBook.getByPK(reply.getId(), connection);
					boolean condition = ((book1.getAuthor().equals(book
							.getAuthor()))
							&& (book1.getName().equals(book.getName()))
							&& (book1.getPublicationDate().equals(book
									.getPublicationDate()))
							&& (!book.getArrivalDate().equals(null)) && (!reply
							.getExecuted()));
					if (condition) {

						reply.setBook(daoBook.getByPK(book.getId(), connection));
						reply.setExecuted(true);
						daoReply.update(reply, connection);

					}
				}
			}

		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String getReplyById(Integer id) {

		GenericDAO<Reply, Integer> dao;
		String replyString = null;
		try {
			dao = factory.getDao(Reply.class);
			replyString = ParseUtil.replyToString(dao.getByPK(id,
					ConnectionManager.getConnection()));
		} catch (PersistException e) {
			logger.error(e);
		}

		return replyString;
	}

}
