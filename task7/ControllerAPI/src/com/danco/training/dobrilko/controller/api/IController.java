package com.danco.training.dobrilko.controller.api;

import java.util.Date;

public interface IController {
	public void readOrdersFromFile();

	public void readBooksFromFile();

	public void readRepliesFromFile();

	public void writeOrdersWithReflection();

	public void writeRepliesWithReflection();

	public void writeBooksWithReflection();

	public double getSumOfExecutedOrders(Date startDate, Date endDate);

	public void readFromFile(String path);

	public void writeInFile(String path);

	public void cloneOrder(int id);

	public void addOrder(String orderString);

	public void cancelOrder(int id);

	public void executeOrder(int id);

	public String getOrdersString();

	public String getExecutedOrdersString(Date startDate, Date endDate);

	public int getNumberOfExecutedOrders(Date startDate, Date endDate);

	public void sortOrdersByDate();

	public void sortOrdersByExecution();

	public void markUnclaimedBooks();

	public void sortOrdersByPrice();

	public String getOrderStringById(int id);

	public void addBook(String bookString);

	public void deleteBook(int id);

	public String getBooks();

	public String getUnclaimedBooks();

	public void sortBookByPublicationDate();

	public void sortBookByName();

	public void sortBookByPrice();

	public String getBookById(int id);

	public String getReplies();

	public void sortRepliesByAlphabet();

	public void sortRepliesByNumber();

	public void addReply(String replyString);

	public void markRepliesAsExecuted();

	public String getReplyById(int id);

}
