package com.danco.training.dobrilko.controller.api;

import java.sql.Connection;
import java.util.Date;

public interface IBookshopController {

	public double getSumOfExecutedOrders(Date startDate, Date endDate);

	public void cloneOrder(Integer id);

	public void addOrder(String orderString);

	public void cancelOrder(Integer id);

	public void executeOrder(Integer id);

	public String getOrdersString();

	public String getExecutedOrdersString(Date startDate, Date endDate);

	public Integer getNumberOfExecutedOrders(Date startDate, Date endDate);

	public String sortOrdersByDate();

	public String sortOrdersByExecution();

	public void markUnclaimedBooks(Connection connection);

	public String sortOrdersByPrice();

	public String getOrderStringById(Integer id);

	public void addBook(String bookString);

	public void deleteBook(Integer id);

	public String getBooks();

	public String getUnclaimedBooks();

	public String sortBookByPublicationDate();

	public String sortBookByName();

	public String sortBookByPrice();

	public String getBookById(Integer id);

	public String getReplies();

	public void sortRepliesByAlphabet();

	public String sortRepliesByNumber();

	public void addReply(String replyString);

	public void markRepliesAsExecuted(Connection connection);

	public String getReplyById(Integer id);

}
