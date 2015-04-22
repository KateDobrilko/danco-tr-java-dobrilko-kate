package com.danco.training.dobrilko.controller.api;

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

	public void sortOrdersByDate();

	public void sortOrdersByExecution();

	public void markUnclaimedBooks();

	public void sortOrdersByPrice();

	public String getOrderStringById(Integer id);

	public void addBook(String bookString);

	public void deleteBook(Integer id);

	public String getBooks();

	public String getUnclaimedBooks();

	public void sortBookByPublicationDate();

	public void sortBookByName();

	public void sortBookByPrice();

	public String getBookById(Integer id);

	public String getReplies();

	public void sortRepliesByAlphabet();

	public void sortRepliesByNumber();

	public void addReply(String replyString);

	public void markRepliesAsExecuted();

	public String getReplyById(Integer id);

}
