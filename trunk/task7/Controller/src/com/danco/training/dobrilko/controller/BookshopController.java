package com.danco.training.dobrilko.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.bookshop.Bookshop;
import com.danco.training.dobrilko.comparator.BookNameComparator;
import com.danco.training.dobrilko.comparator.BookPriceComparator;
import com.danco.training.dobrilko.comparator.BookPublicationDateComparator;
import com.danco.training.dobrilko.comparator.OrderDateComparator;
import com.danco.training.dobrilko.comparator.OrderExecutedComparator;
import com.danco.training.dobrilko.comparator.OrderPriceComparator;
import com.danco.training.dobrilko.comparator.ReplyAlphabetComparator;
import com.danco.training.dobrilko.comparator.ReplyNumberComparator;
import com.danco.training.dobrilko.controller.api.IController;
import com.danco.training.dobrilko.controller.property.PropertyStorage;
import com.danco.training.dobrilko.database.OrderBase;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Order;
import com.danco.training.dobrilko.entity.Reply;
import static  com.danco.training.dobrilko.other.ParseUtil.*;
import com.danco.training.dobrilko.other.SerializerUtil;
import com.danco.training.dobrilko.processor.AnnotationProcessor;

public class BookshopController implements IController {

	public BookshopController() {
	}

	public void readOrdersFromFile() throws IllegalArgumentException {
		try {
			Object[] orders = AnnotationProcessor.getInstance().readFromFile(
					Order.class);
			for (Object order : orders) {
				if (order instanceof Order)
					Bookshop.getInstance().getOrderBase().add((Order) order);
			}
		} catch (IllegalAccessException | NoSuchFieldException
				| SecurityException | NoSuchMethodException
				| InstantiationException | InvocationTargetException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Error during reading orders from file!");
		}

	}

	public void readBooksFromFile() throws IllegalArgumentException {
		try {
			Object[] books = AnnotationProcessor.getInstance().readFromFile(
					Book.class);
			for (Object book : books) {
				if (book instanceof Book) {
					Bookshop.getInstance().getBookBase().add((Book) book);
				}
			}
		} catch (IllegalAccessException | NoSuchFieldException
				| SecurityException | NoSuchMethodException
				| InstantiationException | InvocationTargetException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Error during reading books from file!");
		}
	}

	public void readRepliesFromFile() throws IllegalArgumentException {
		try {
			Object[] replies = AnnotationProcessor.getInstance().readFromFile(
					Reply.class);
			for (Object reply : replies) {
				if (reply instanceof Reply) {
					Bookshop.getInstance().getReplyBase().add((Reply) reply);
				}
			}
		} catch (IllegalAccessException | NoSuchFieldException
				| SecurityException | NoSuchMethodException
				| InstantiationException | InvocationTargetException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Error during reading replies from file!", e);
		}
	}

	public void writeOrdersWithReflection() {

		try {

			AnnotationProcessor.getInstance().writeInFile(
					Bookshop.getInstance().getOrderBase().getOrdersArray());
		} catch (IllegalArgumentException | IllegalAccessException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("IO error detected!", e);
		} catch (NoSuchFieldException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("NoSuchField error detected!", e);
		} catch (SecurityException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Security error detected!", e);
		}
	}

	public void writeRepliesWithReflection() {

		try {

			AnnotationProcessor.getInstance().writeInFile(
					Bookshop.getInstance().getReplyBase().getRepliesArray());
		} catch (IllegalArgumentException | IllegalAccessException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("IO error detected!", e);
		} catch (NoSuchFieldException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("NoSuchField error detected!", e);
		} catch (SecurityException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Security error detected!", e);
		}
	}

	public void writeBooksWithReflection() {

		try {

			AnnotationProcessor.getInstance().writeInFile(
					Bookshop.getInstance().getBookBase().getBooksArray());
		} catch (IllegalArgumentException | IllegalAccessException
				| IOException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("IO error detected!", e);
		} catch (NoSuchFieldException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("NoSuchField error detected!", e);
		} catch (SecurityException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Security error detected!", e);
		}
	}

	public void readFromFile(String path) {
		SerializerUtil.ReadFromFile(path);
	}

	public void writeInFile(String path) {
		SerializerUtil.WriteInFile(path);
	}

	public void cloneOrder(int id) {
		try {
			Bookshop.getInstance()
					.getOrderBase()
					.add(Bookshop.getInstance().getOrderBase().getById(id)
							.clone());
		} catch (CloneNotSupportedException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Cannot clone order with id " + id);

		}
	}

	public void addOrder(String orderString) {

		Bookshop.getInstance().getOrderBase()
				.add(parseOrderString(orderString));
	}

	public void cancelOrder(int id) {

		Order order = getOrderById(id);
		if (order.getStatus()) {
			for (Book book : order.getBooks()) {
				book.setOrdered(false);
			}
			Bookshop.getInstance().getOrderBase().delete(id);

		} else {
			Logger logger = Logger.getLogger(OrderBase.class);
			logger.warn("Order with id:" + Integer.toString(id)
					+ " is already executed.");

		}

	}

	public void executeOrder(int id) {

		int count = 0;

		Order orderToExecute = null;
		for (Order order : Bookshop.getInstance().getOrderBase().getOrders()) {

			if ((order.getId() == id) && (order.getStatus())) {

				for (Book orderBook : order.getBooks()) {
					for (Book bookBaseBook : Bookshop.getInstance()
							.getBookBase().getBooks()) {
						orderBook.setOrdered(true);
						if ((orderBook.getId() == bookBaseBook.getId())
								&& (bookBaseBook.getDateOfAddition() != null)
								&& (bookBaseBook.getNumberOfExemplars() != 0)) {

							count++;

							break;
						}
					}

				}

				if (count == order.getBooks().size()) {
					orderToExecute = order;
					orderToExecute.setStatus(false);
					orderToExecute.setDateOfExecution(new Date());
					for (Book orderBook : orderToExecute.getBooks()) {
						for (Book bookBaseBook : Bookshop.getInstance()
								.getBookBase().getBooks()) {
							if (orderBook.getId() == bookBaseBook.getId()
									&& (bookBaseBook.getDateOfAddition() != null)
									&& (bookBaseBook.getNumberOfExemplars() != 0)) {
								bookBaseBook.setOrdered(false);
								Bookshop.getInstance().getBookBase()
										.delete(bookBaseBook.getId());

								break;

							}

						}
					}
				} else {
					Logger logger = Logger.getLogger(OrderBase.class);
					logger.warn("Order with id:" + Integer.toString(id)
							+ " cannot be completed.");
				}

			} else {
				Logger logger = Logger.getLogger(OrderBase.class);
				logger.warn("Order with id:"
						+ Integer.toString(id)
						+ " doesn't exist or already executed. No execution performed.");

			}
		}

	}

	public String getOrdersString() {
		StringBuilder sb = new StringBuilder();

		for (Order order : Bookshop.getInstance().getOrderBase().getOrders()) {
			sb.append(orderToString(order));
			sb.append(System.lineSeparator());
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
		for (Order order : Bookshop.getInstance().getOrderBase().getOrders()) {
			if (order.getStatus()) {
				executed.add(order);
			}
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

	public int getNumberOfExecutedOrders(Date startDate, Date endDate) {
		return getExecutedOrders(startDate, endDate).size();
	}

	public void sortOrdersByDate() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderDateComparator());

	}

	public void sortOrdersByExecution() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderExecutedComparator());

	}

	@SuppressWarnings("deprecation")
	public void markUnclaimedBooks() {

		for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
			if (book.getDateOfAddition() != null) {
				Date date = new Date();

				LocalDate ldcurr = LocalDate.of(date.getYear() + 1900,
						date.getMonth() + 1, date.getDate());
				LocalDate ld = LocalDate.of(book.getDateOfAddition().getYear(),
						book.getDateOfAddition().getMonth(), book
								.getDateOfAddition().getDate());
				Period period = Period.between(ld, ldcurr);

				if (period.getMonths() >= PropertyStorage.getInstance().getMonthsToMarkUnclaimed()) {
					book.setUnclaimed(true);
				}
			}
		}
	}

	public void sortOrdersByPrice() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderPriceComparator());

	}

	public String getOrderStringById(int id) {
		return orderToString(Bookshop.getInstance().getOrderBase().getById(id));
	}

	private Order getOrderById(int id) {
		return Bookshop.getInstance().getOrderBase().getById(id);
	}

	public void addBook(String bookString) {

		if (PropertyStorage.getInstance().getMarkRepliesAsExecuted()) {
			Bookshop.getInstance().getBookBase()
					.add(parseBookString(bookString));
			markRepliesAsExecuted();
		}

		else {
			Bookshop.getInstance().getBookBase()
					.add(parseBookString(bookString));
		}

	}

	public void deleteBook(int id) {

		boolean result = Bookshop.getInstance().getBookBase().delete(id);
		if (!result) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.warn("Book with id "
					+ Integer.toString(id)
					+ "was not deleted. Book is ordered or there is no book with such id.");

		}
	}

	public String getBooks() {
		StringBuilder sb = new StringBuilder();
		for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
			sb.append(bookToString(book));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String getUnclaimedBooks() {
		StringBuilder sb = new StringBuilder();
		markUnclaimedBooks();
		for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
			if (book.isUnclaimed()) {
				sb.append(bookToString(book));
				sb.append(System.lineSeparator());

			}
		}

		return sb.toString();
	}

	public void sortBookByPublicationDate() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookPublicationDateComparator());
	}

	public void sortBookByName() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookNameComparator());
	}

	public void sortBookByPrice() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookPriceComparator());
	}

	public String getBookById(int id) {
		return bookToString(Bookshop.getInstance().getBookBase().getById(id));
	}

	public String getReplies() {
		StringBuilder sb = new StringBuilder();
		for (Reply reply : Bookshop.getInstance().getReplyBase().getReplies()) {
			sb.append(replyToString(reply));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public void sortRepliesByAlphabet() {
		Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
				new ReplyAlphabetComparator());
	}

	public void sortRepliesByNumber() {
		Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
				new ReplyNumberComparator());
	}

	public void addReply(String replyString) {
		Bookshop.getInstance().getReplyBase()
				.add(parseReplyString(replyString));

	}

	public void markRepliesAsExecuted() {

		for (Reply reply : Bookshop.getInstance().getReplyBase().getReplies()) {
			for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
				boolean condition = ((reply.getBook().getAuthor().equals(book
						.getAuthor()))
						&& (reply.getBook().getName().equals(book.getName()))
						&& (reply.getBook().getDateOfPublication().equals(book
								.getDateOfPublication()))
						&& (!book.getDateOfAddition().equals(null)) && (!reply
						.isExecuted()));
				if (condition) {

					reply.setBook(book);
					reply.setExecuted(true);

				}
			}
		}

	}

	public String getReplyById(int id) {
		return replyToString(Bookshop.getInstance().getReplyBase().getById(id));
	}

	
}
