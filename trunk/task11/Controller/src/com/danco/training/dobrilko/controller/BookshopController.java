package com.danco.training.dobrilko.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.connection.ConnectionManager;
import com.danco.training.dobrilko.controller.api.IBookshopController;
import com.danco.training.dobrilko.controller.property.PropertyStorage;
import com.danco.training.dobrilko.dao.GenericDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;

import static com.danco.training.dobrilko.other.ParseUtil.*;

import com.danco.training.dobrilko.mysql.MySqlBookDao;
import com.danco.training.dobrilko.mysql.MySqlOrderDao;
import com.danco.training.dobrilko.mysql.MySqlReplyDao;

public class BookshopController implements IBookshopController {

	private Logger logger = Logger.getLogger(BookshopController.class);

	private Connection connection;

	public BookshopController() {

	}


	public void cloneOrder(Integer id) {

		GenericDao<Order, Integer> dao = new MySqlOrderDao();
		;
		try {

			Connection connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			dao.persist(dao.getByPK(id, connection).clone(), connection);
			connection.commit();
		} catch (PersistException | CloneNotSupportedException | SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(e);
			}
			logger.error(e);
		}

	}

	
	public synchronized void addOrder(String orderString) {

		GenericDao<Order, Integer> dao = new MySqlOrderDao();
		try {

			dao.create(ConnectionManager.getConnection());
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public synchronized void cancelOrder(Integer id) {

		GenericDao<Order, Integer> daoOrder;
		GenericDao<Book, Integer> daoBook;
		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			daoOrder = new MySqlOrderDao();
			daoBook = new MySqlBookDao();
			Order order = daoOrder.getByPK(id, connection);
			if (order.getStatus()) {
				for (Book book : daoBook.getAll("id", connection)) {
					book.setOrdered(false);
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

	
	public synchronized void executeOrder(Integer id) {

		GenericDao<Order, Integer> daoOrder = new MySqlOrderDao();
		GenericDao<Book, Integer> daoBook = new MySqlBookDao();
		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);

			Order order = daoOrder.getByPK(id, connection);
			for (Book b : daoBook.getAll("id", connection)) {
				if (b.getOrder().equals(order)) {
					order.setSum(order.getSum() + b.getPrice());
					daoBook.delete(daoBook.getByPK(b.getId(), connection),
							connection);
				}
			}

			order.setStatus(true);
			order.setDateOfExecution(new Date());
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

	
	public String getOrdersString() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Order, Integer> daoOrder = new MySqlOrderDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("id", connection)) {
				sb.append(orderToString(order));
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

	
	private ArrayList<Order> getExecutedOrders() {

		ArrayList<Order> executed = new ArrayList<Order>();

		GenericDao<Order, Integer> dao;
		Connection connection = ConnectionManager.getConnection();
		try {
			dao = new MySqlOrderDao();

			for (Order order : dao.getAll("id", connection)) {
				if (order.getStatus()) {
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
			boolean condition1 = order.getDateOfExecution().after(startDate);
			boolean condition2 = order.getDateOfExecution().before(endDate);
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
			boolean condition1 = order.getDateOfExecution().after(startDate);
			boolean condition2 = order.getDateOfExecution().before(endDate);
			if ((condition1 == true) && (condition2 == true)) {
				sb.append(orderToString(order));
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

		GenericDao<Order, Integer> daoOrder = new MySqlOrderDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("dateOfExecution", connection)) {
				sb.append(orderToString(order));
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

	public String sortOrdersByExecution() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Order, Integer> daoOrder = new MySqlOrderDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("status", connection)) {
				sb.append(orderToString(order));
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

	
	@SuppressWarnings("deprecation")
	public void markUnclaimedBooks(Connection connection) {
		GenericDao<Book, Integer> dao;

		dao = new MySqlBookDao();
		try {
			for (Book book : dao.getAll("id", connection)) {
				if (book.getDateOfAddition() != null) {
					Date date = new Date();

					LocalDate ldcurr = LocalDate.of(date.getYear() + 1900,
							date.getMonth() + 1, date.getDate());
					LocalDate ld = LocalDate.of(book.getDateOfAddition()
							.getYear(), book.getDateOfAddition().getMonth(),
							book.getDateOfAddition().getDate());
					Period period = Period.between(ld, ldcurr);

					if (period.getMonths() >= PropertyStorage.getInstance()
							.getMonthsToMarkUnclaimed()) {
						book.setUnclaimed(true);
					}

					dao.update(book, connection);
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public String sortOrdersByPrice() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Order, Integer> daoOrder = new MySqlOrderDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Order order : daoOrder.getAll("sum", connection)) {
				sb.append(orderToString(order));
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

	
	public String getOrderStringById(Integer id) {
		GenericDao<Order, Integer> dao;
		Connection connection = ConnectionManager.getConnection();
		String out = null;
		try {

			dao = new MySqlOrderDao();
			out = orderToString(dao.getByPK(id, connection));
		} catch (PersistException e) {
			logger.error(e);
		}
		return out;
	}

	
	public synchronized void addBook(String bookString) {

		GenericDao<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			dao = new MySqlBookDao();

			if (PropertyStorage.getInstance().getMarkRepliesAsExecuted()) {
				dao.persist(parseBookString(bookString), connection);
				markRepliesAsExecuted(connection);
			}

			else {
				dao.persist(parseBookString(bookString), connection);
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

	
	public synchronized void deleteBook(Integer id) {

		GenericDao<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();
		try {
			connection.setAutoCommit(false);
			dao = new MySqlBookDao();

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

	
	public String getBooks() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Book, Integer> dao;
		Connection connection = ConnectionManager.getConnection();

		try {
			dao = new MySqlBookDao();
			for (Book book : dao.getAll("id", connection)) {
				sb.append(bookToString(book));
				sb.append(System.lineSeparator());
			}
		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	public String getUnclaimedBooks() {
		StringBuilder sb = new StringBuilder();
		Connection connection = ConnectionManager.getConnection();

		GenericDao<Book, Integer> dao;
		try {
			connection.setAutoCommit(false);
			markUnclaimedBooks(connection);
			dao = new MySqlBookDao();

			for (Book book : dao.getAll("id", connection)) {
				if (book.isUnclaimed()) {
					sb.append(bookToString(book));
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

	public String sortBookByPublicationDate() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Book, Integer> daoBook = new MySqlBookDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("dateOfPublication", connection)) {
				sb.append(bookToString(book));
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

	public String sortBookByName() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Book, Integer> daoBook = new MySqlBookDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("name", connection)) {
				sb.append(bookToString(book));
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

	public String sortBookByPrice() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Book, Integer> daoBook = new MySqlBookDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Book book : daoBook.getAll("price", connection)) {
				sb.append(bookToString(book));
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

	
	public String getBookById(Integer id) {
		String bookString = null;

		GenericDao<Book, Integer> dao;

		try {
			dao = new MySqlBookDao();

			bookString = bookToString(dao.getByPK(id,
					ConnectionManager.getConnection()));
		} catch (PersistException e) {
			logger.error(e);
		}

		return bookString;
	}

	
	public String getReplies() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Reply, Integer> dao;

		try {
			dao = new MySqlReplyDao();
			for (Reply reply : dao.getAll("id",
					ConnectionManager.getConnection())) {
				sb.append(replyToString(reply));
				sb.append(System.lineSeparator());
			}

		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	public void sortRepliesByAlphabet() {

	}

	public String sortRepliesByNumber() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Reply, Integer> daoReply = new MySqlReplyDao();

		Connection connection = ConnectionManager.getConnection();

		try {
			connection.setAutoCommit(false);
			for (Reply reply : daoReply.getAll("numberOfRequests", connection)) {
				sb.append(replyToString(reply));
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

	
	public synchronized void addReply(String replyString) {

		GenericDao<Reply, Integer> dao;

		try {
			dao = new MySqlReplyDao();
			dao.persist(parseReplyString(replyString),
					ConnectionManager.getConnection());

		} catch (PersistException e) {
			logger.error(e);
		}

	}

	
	public void markRepliesAsExecuted(Connection connection) {
		GenericDao<Book, Integer> daoBook;
		GenericDao<Reply, Integer> daoReply;

		try {
			daoReply = new MySqlReplyDao();

			daoBook = new MySqlBookDao();
			for (Reply reply : daoReply.getAll("id", connection)) {
				for (Book book : daoBook.getAll("id", connection)) {

					Book book1 = daoBook.getByPK(reply.getId(), connection);
					boolean condition = ((book1.getAuthor().equals(book
							.getAuthor()))
							&& (book1.getName().equals(book.getName()))
							&& (book1.getDateOfPublication().equals(book
									.getDateOfPublication()))
							&& (!book.getDateOfAddition().equals(null)) && (!reply
							.isExecuted()));
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

	
	public String getReplyById(Integer id) {

		GenericDao<Reply, Integer> dao;
		String replyString = null;
		try {
			dao = new MySqlReplyDao();
			replyString = replyToString(dao.getByPK(id,
					ConnectionManager.getConnection()));
		} catch (PersistException e) {
			logger.error(e);
		}

		return replyString;
	}

}
