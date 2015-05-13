package com.danco.training.dobrilko.Controller;

import java.util.Date;

import org.apache.log4j.Logger;
import com.danco.training.dobrilko.API.IBookshopController;
import com.danco.training.dobrilko.Controller.transactionlayer.BookTransactionLayer;
import com.danco.training.dobrilko.Controller.transactionlayer.OrderTransactionLayer;
import com.danco.training.dobrilko.Controller.transactionlayer.ReplyTransactionLayer;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;

public class BookshopController implements IBookshopController {

	private Logger logger = Logger.getLogger(BookshopController.class);

	private BookTransactionLayer bsl = new BookTransactionLayer();
	private OrderTransactionLayer osl = new OrderTransactionLayer();
	private ReplyTransactionLayer rsl = new ReplyTransactionLayer();

	public double getSumOfExecutedOrders(Date startDate, Date endDate) {
		double result = 0;
		try {
			result = osl.getSumOfExecutedOrders();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public void addOrder(String orderString) {

		try {
			osl.addOrder(orderString);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public void cancelOrder(Integer id) {
		try {
			osl.cancelOrder(id);
		} catch (PersistException e) {
			logger.error(e);
		}
	}

	public void executeOrder(Integer id) {
		try {
			osl.executeOrder(id);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public String getOrdersString() {
		String result = "";
		try {
			result = osl.getOrdersString();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String getExecutedOrdersString() {
		String result = "";
		result = osl.getExecutedOrdersString();
		return result;
	}

	public Integer getNumberOfExecutedOrders() {
		Integer result = 0;
		result = osl.getNumberOfExecutedOrders();
		return result;
	}

	public String sortOrdersByDate() {
		String result = "";
		try {
			result = osl.sortOrdersByDate();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String sortOrdersByExecution() {
		String result = "";
		try {
			result = osl.sortOrdersByExecution();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public void addBook(String bookString) {
		try {
			bsl.addBook(bookString);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public void deleteBook(Integer id) {
		try {
			bsl.deleteBook(id);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public String getBooks() {
		String result = "";
		try {
			result = bsl.getBooks();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String getUnclaimedBooks() {
		String result = "";
		try {
			result = bsl.getUnclaimedBooks();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String sortBookByPublicationDate() {
		String result = "";
		try {
			result = bsl.sortBookByPublicationDate();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String sortBookByName() {
		String result = "";
		try {
			result = bsl.sortBookByName();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String sortBookByPrice() {
		String result = "";
		try {
			result = bsl.sortBookByPrice();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String getReplies() {
		String result = "";
		try {
			result = rsl.getReplies();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public String sortRepliesByNumber() {
		String result = "";
		try {
			result = rsl.sortRepliesByNumber();
		} catch (PersistException e) {
			logger.error(e);
		}
		return result;
	}

	public void addReply(String replyString) {

		try {
			rsl.addReply(replyString);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public void markRepliesAsExecuted() {
		try {
			rsl.markRepliesAsExecuted();
		} catch (PersistException e) {
			logger.error(e);
		}

	}

}
