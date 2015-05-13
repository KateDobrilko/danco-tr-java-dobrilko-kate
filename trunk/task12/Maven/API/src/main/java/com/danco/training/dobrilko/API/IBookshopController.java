package com.danco.training.dobrilko.API;

import java.util.Date;

public interface IBookshopController {

	public double getSumOfExecutedOrders(Date startDate, Date endDate);

	public void addOrder(String orderString);

	public void cancelOrder(Integer id);

	public void executeOrder(Integer id);

	public String getOrdersString();

	public String getExecutedOrdersString();

	public Integer getNumberOfExecutedOrders();

	public String sortOrdersByDate();

	public String sortOrdersByExecution();

	public void addBook(String bookString);

	public void deleteBook(Integer id);

	public String getBooks();

	public String getUnclaimedBooks();

	public String sortBookByPublicationDate();

	public String sortBookByName();

	public String sortBookByPrice();

	public String getReplies();

	public String sortRepliesByNumber();

	public void addReply(String replyString);

	public void markRepliesAsExecuted();

}
