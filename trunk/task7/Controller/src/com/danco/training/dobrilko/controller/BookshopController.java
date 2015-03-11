package com.danco.training.dobrilko.controller;

import java.io.IOException;
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
import com.danco.training.dobrilko.database.OrderBase;
import com.danco.training.dobrilko.entitiy.Book;
import com.danco.training.dobrilko.entitiy.Order;
import com.danco.training.dobrilko.entitiy.Reply;
import com.danco.training.dobrilko.other.CSVImportExportUtil;
import com.danco.training.dobrilko.other.SerializerUtil;
import com.danco.training.dobrilko.processor.AnnotationProcessor;
import com.danco.training.dobrilko.property.PropertyStorage;


public class BookshopController {
	
	
	public static void readOrdersFromFile(String csvPath)
			throws IllegalArgumentException {
		CSVImportExportUtil.ImportOrders(csvPath);
	}

	public static void readBooksFromFile(String csvPath)
			throws IllegalArgumentException {
		CSVImportExportUtil.ImportBooks(csvPath);
	}

	public static void readRepliesFromFile(String csvPath)
			throws IllegalArgumentException {
		CSVImportExportUtil.ImportReplies(csvPath);
	}

	public static void writeBookToFile(String csvPath, Book book)
			throws IllegalArgumentException {
		CSVImportExportUtil.ExportBook(book, csvPath);
	}

	public static void writeOrderToFile(String csvPath, Order order)
			throws IllegalArgumentException {
		CSVImportExportUtil.ExportOrder(order, csvPath);
	}

	public static void writeOrdersWithReflection() {
		
		
		try {
			
			AnnotationProcessor.getInstance().writeInFile(Bookshop.getInstance().getOrderBase().getOrdersArray());
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

	public static void writeReplyToFile(String csvPath, Reply reply)
			throws IllegalArgumentException {
		CSVImportExportUtil.ExportReply(reply, csvPath);
	}

	public static void readFromFile(String path) {
		SerializerUtil.ReadFromFile(path);
	}

	public static void writeInFile(String path) {
		SerializerUtil.WriteInFile(path);
	}

	public static Order cloneOrder(int id) {
		try {
			return Bookshop.getInstance().getOrderBase().getById(id).clone();
		} catch (CloneNotSupportedException e) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.error("Cannot clone order with id " + id);
			return null;
		}
	}

	public static void addOrder(Order order) {

		Bookshop.getInstance().getOrderBase().add(order);
	}

	public static void cancelOrder(int id) {

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

	public static void executeOrder(int id) {

		int count = 0;

		Order orderToExecute = null;
		for (Order order : Bookshop.getInstance().getOrderBase().getOrders()) {

			if ((order.getId() == id) && (order.getStatus())) {

				for (Book orderBook : order.getBooks()) {
					for (Book bookBaseBook : Bookshop.getInstance()
							.getBookBase().getBooks()) {
						orderBook.setOrdered(true);
						if (orderBook.getId() == bookBaseBook.getId()) {

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
							if (orderBook.getId() == bookBaseBook.getId()) {
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

	public static ArrayList<Order> getOrders() {
		return Bookshop.getInstance().getOrderBase().getOrders();
	}

	public static double getSumOfExecutedOrders(Date startDate, Date endDate) {
		double sum = 0;

		for (Order order : getExecutedOrders(startDate, endDate)) {

			sum += order.getSum();
		}
		return sum;
	}

	public static ArrayList<Order> getExecutedOrders() {
		ArrayList<Order> executed = new ArrayList<Order>();
		for (Order order : Bookshop.getInstance().getOrderBase().getOrders()) {
			if (!order.getStatus()) {
				executed.add(order);
			}
		}
		return executed;
	}

	public static ArrayList<Order> getExecutedOrders(Date startDate,
			Date endDate) {
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

	public static int getNumberOfExecutedOrders(Date startDate, Date endDate) {
		return getExecutedOrders(startDate, endDate).size();
	}

	public static void sortOrdersByDate() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderDateComparator());

	}

	public static void sortOrdersByExecution() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderExecutedComparator());

	}

	@SuppressWarnings("deprecation")
	public static void markUnclaimedBooks() {

		for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
			Date date = new Date();

			LocalDate ldcurr = LocalDate.of(date.getYear() + 1900,
					date.getMonth() + 1, date.getDate());
			LocalDate ld = LocalDate.of(book.getDateOfAddition().getYear(),
					book.getDateOfAddition().getMonth(), book
							.getDateOfAddition().getDate());
			Period period = Period.between(ld, ldcurr);

			if (period.getMonths() >= PropertyStorage.getInstance()
					.getMonthsToMarkUnclaimed()) {
				book.setUnclaimed(true);
			}
		}
	}

	public static void sortOrdersByPrice() {
		Bookshop.getInstance().getOrderBase().getOrders()
				.sort(new OrderPriceComparator());

	}

	public static Order getOrderById(int id) {
		return Bookshop.getInstance().getOrderBase().getById(id);
	}

	public static void addBook(Book book) {

		if (PropertyStorage.getInstance().getMarkRepliesAsExecuted()) {
			Bookshop.getInstance().getBookBase().add(book);
			markRepliesAsExecuted();
		}

		else {
			Bookshop.getInstance().getBookBase().add(book);
		}

	}

	public static void deleteBook(int id) {

		boolean result = Bookshop.getInstance().getBookBase().delete(id);
		if (!result) {
			Logger logger = Logger.getLogger(BookshopController.class);
			logger.warn("Book with id "
					+ Integer.toString(id)
					+ "was not deleted. Book is ordered or there is no book with such id.");

		}
	}

	public static ArrayList<Book> getBooks() {
		return Bookshop.getInstance().getBookBase().getBooks();
	}

	public static ArrayList<Book> getUnclaimedBooks() {
		markUnclaimedBooks();
		ArrayList<Book> unclaimedBooks = new ArrayList<Book>();
		for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
			if (book.isUnclaimed()) {
				unclaimedBooks.add(book);
			}
		}
		return unclaimedBooks;
	}

	public static void sortBookByPublicationDate() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookPublicationDateComparator());
	}

	public static void sortBookByName() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookNameComparator());
	}

	public static void sortBookByPrice() {
		Collections.sort(Bookshop.getInstance().getBookBase().getBooks(),
				new BookPriceComparator());
	}

	public static Book getBookById(int id) {
		return Bookshop.getInstance().getBookBase().getById(id);
	}

	public static ArrayList<Reply> getReplies() {
		return Bookshop.getInstance().getReplyBase().getReplies();
	}

	public static void sortRepliesByAlphabet() {
		Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
				new ReplyAlphabetComparator());
	}

	public static void sortRepliesByNumber() {
		Collections.sort(Bookshop.getInstance().getReplyBase().getReplies(),
				new ReplyNumberComparator());
	}

	public static void addReply(Reply reply) {
		Bookshop.getInstance().getReplyBase().add(reply);

	}

	public static void markRepliesAsExecuted() {

		for (Reply reply : Bookshop.getInstance().getReplyBase().getReplies()) {
			for (Book book : Bookshop.getInstance().getBookBase().getBooks()) {
				boolean condition = ((reply.getBook().getAuthor().equals(book
						.getAuthor()))
						&& (reply.getBook().getName().equals(book.getName()))
						&& (reply.getBook().getDateOfPublication().equals(book
								.getDateOfPublication())) && (!book
						.getDateOfAddition().equals(null)));
				if (condition) {

					reply.setBook(book);
					reply.setExecuted(true);

				}
			}
		}

	}

	public static Reply getReplyById(int id) {
		return Bookshop.getInstance().getReplyBase().getById(id);
	}
}
