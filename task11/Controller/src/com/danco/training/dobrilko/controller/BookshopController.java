package com.danco.training.dobrilko.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.api.IBookshopController;
import com.danco.training.dobrilko.controller.property.PropertyStorage;
import com.danco.training.dobrilko.dao.DaoFactory;
import com.danco.training.dobrilko.dao.GenericDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;

import static com.danco.training.dobrilko.other.ParseUtil.*;

import com.danco.training.dobrilko.mysql.MySqlDaoFactory;

public class BookshopController implements IBookshopController {

	private Logger logger = Logger.getLogger(BookshopController.class);
	private DaoFactory<Connection> factory = new MySqlDaoFactory();
	private Connection connection;

	public BookshopController() {
		try {
			connection = factory.getContext();
			connection.setAutoCommit(false);
		} catch (PersistException | SQLException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public void cloneOrder(Integer id) {

		GenericDao<Order, Integer> dao;
		try {
			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));
			dao.persist(dao.getByPK(id).clone());
		} catch (PersistException | CloneNotSupportedException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void addOrder(String orderString) {

		GenericDao<Order, Integer> dao;
		try {
			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));
			dao.create();
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void cancelOrder(Integer id) {

		GenericDao<Order, Integer> daoOrder;
		GenericDao<Book, Integer> daoBook;

		try {
			daoOrder = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));

			daoBook = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));
			Order order = getOrderById(id);
			if (order.getStatus()) {
				for (Book book : daoBook.getAll()) {
					book.setOrdered(false);
				}
				daoOrder.delete(daoOrder.getByPK(id));

			} else {
				Logger logger = Logger.getLogger(BookshopController.class);
				logger.warn("Order with id:" + Integer.toString(id)
						+ " is already executed.");

			}
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void executeOrder(Integer id) {

		GenericDao<Order, Integer> daoOrder;
		GenericDao<Book, Integer> daoBook;

		try {
			daoOrder = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));

			daoBook = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			Order order = daoOrder.getByPK(id);
			daoBook.delete(daoBook.getByPK(order.getBookId()));
			order.setStatus(true);
			order.setDateOfExecution(new Date());
			daoOrder.update(order);
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

		GenericDao<Order, Integer> dao;
		try {
			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));
			for (Order order : dao.getAll()) {
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

	@SuppressWarnings("unchecked")
	private ArrayList<Order> getExecutedOrders() {

		ArrayList<Order> executed = new ArrayList<Order>();

		GenericDao<Order, Integer> dao;
		try {
			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));

			for (Order order : dao.getAll()) {
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

	public void sortOrdersByDate() {
		/*
		 * Bookshop.getInstance().getOrderBase().getOrders() .sort(new
		 * OrderDateComparator());
		 */
	}

	public void sortOrdersByExecution() {
		/*
		 * Bookshop.getInstance().getOrderBase().getOrders() .sort(new
		 * OrderExecutedComparator());
		 */

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void markUnclaimedBooks() {
		GenericDao<Book, Integer> dao;
		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			for (Book book : dao.getAll()) {
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
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}
	}

	public void sortOrdersByPrice() {
		/*
		 * Bookshop.getInstance().getOrderBase().getOrders() .sort(new
		 * OrderPriceComparator());
		 */

	}

	@SuppressWarnings("unchecked")
	public String getOrderStringById(Integer id) {
		GenericDao<Order, Integer> dao;
		String out = null;
		try {

			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));
			out = orderToString(dao.getByPK(id));
		} catch (PersistException e) {
			logger.error(e);
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	private Order getOrderById(Integer id) {

		GenericDao<Order, Integer> dao;
		Order order = null;
		try {
			dao = (GenericDao<Order, Integer>) (factory.getDao(connection,
					Order.class));

			order = dao.getByPK(id);
		} catch (PersistException e) {
			logger.error(e);
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	public synchronized void addBook(String bookString) {

		GenericDao<Book, Integer> dao;

		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			if (PropertyStorage.getInstance().getMarkRepliesAsExecuted()) {
				dao.persist(parseBookString(bookString));
				markRepliesAsExecuted();
			}

			else {
				dao.persist(parseBookString(bookString));
			}
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public synchronized void deleteBook(Integer id) {

		GenericDao<Book, Integer> dao;
		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			dao.delete(dao.getByPK(id));
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String getBooks() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Book, Integer> dao;

		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));
			for (Book book : dao.getAll()) {
				sb.append(bookToString(book));
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
		markUnclaimedBooks();
		GenericDao<Book, Integer> dao;
		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			for (Book book : dao.getAll()) {
				if (book.isUnclaimed()) {
					sb.append(bookToString(book));
					sb.append(System.lineSeparator());

				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	public void sortBookByPublicationDate() {
		/*
		 * Collections.sort(Bookshop.getInstance().getBookBase().getBooks(), new
		 * BookPublicationDateComparator());
		 */
	}

	public void sortBookByName() {
		/*
		 * Collections.sort(Bookshop.getInstance().getBookBase().getBooks(), new
		 * BookNameComparator());
		 */
	}

	public void sortBookByPrice() {
		/*
		 * Collections.sort(Bookshop.getInstance().getBookBase().getBooks(), new
		 * BookPriceComparator());
		 */
	}

	@SuppressWarnings("unchecked")
	public String getBookById(Integer id) {
		String bookString = null;

		GenericDao<Book, Integer> dao;

		try {
			dao = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));

			bookString = bookToString(dao.getByPK(id));
		} catch (PersistException e) {
			logger.error(e);
		}

		return bookString;
	}

	@SuppressWarnings("unchecked")
	public String getReplies() {
		StringBuilder sb = new StringBuilder();

		GenericDao<Reply, Integer> dao;

		try {
			dao = (GenericDao<Reply, Integer>) (factory.getDao(connection,
					Reply.class));
			for (Reply reply : dao.getAll()) {
				sb.append(replyToString(reply));
				sb.append(System.lineSeparator());
			}

		} catch (PersistException e) {
			logger.error(e);
		}

		return sb.toString();
	}

	public void sortRepliesByAlphabet() {
		/*
		 * Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
		 * new ReplyAlphabetComparator());
		 */
	}

	public void sortRepliesByNumber() {
		/*
		 * Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
		 * new ReplyNumberComparator());
		 */
	}

	@SuppressWarnings("unchecked")
	public synchronized void addReply(String replyString) {

		GenericDao<Reply, Integer> dao;

		try {
			dao = (GenericDao<Reply, Integer>) (factory.getDao(connection,
					Reply.class));
			dao.persist(parseReplyString(replyString));

		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public void markRepliesAsExecuted() {
		GenericDao<Book, Integer> daoBook;
		GenericDao<Reply, Integer> daoReply;

		try {
			daoReply = (GenericDao<Reply, Integer>) (factory.getDao(connection,
					Reply.class));

			daoBook = (GenericDao<Book, Integer>) (factory.getDao(connection,
					Book.class));
			for (Reply reply : daoReply.getAll()) {
				for (Book book : daoBook.getAll()) {

					Book book1 = daoBook.getByPK(reply.getBookId());
					boolean condition = ((book1.getAuthor().equals(book
							.getAuthor()))
							&& (book1.getName().equals(book.getName()))
							&& (book1.getDateOfPublication().equals(book
									.getDateOfPublication()))
							&& (!book.getDateOfAddition().equals(null)) && (!reply
							.isExecuted()));
					if (condition) {

						reply.setBookId(book.getId());
						reply.setExecuted(true);
						daoReply.update(reply);

					}
				}
			}
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	@SuppressWarnings("unchecked")
	public String getReplyById(Integer id) {

		GenericDao<Reply, Integer> dao;
		String replyString = null;
		try {
			dao = (GenericDao<Reply, Integer>) (factory.getDao(connection,
					Reply.class));
			replyString = replyToString(dao.getByPK(id));
		} catch (PersistException e) {
			logger.error(e);
		}

		return replyString;
	}

}
