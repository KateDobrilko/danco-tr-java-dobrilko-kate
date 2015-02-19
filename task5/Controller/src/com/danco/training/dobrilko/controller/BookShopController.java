package com.danco.training.dobrilko.controller;

import java.util.ArrayList;

import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.other.Parser;
import com.danco.training.dobrilko.bookshop.service.BookShop;
import com.danco.training.dobrilko.bookshop.service.OrderingSystem;
import com.danco.training.dobrilko.bookshop.service.ReplySystem;
import com.danco.training.dobrilko.bookshop.service.Store;

public class BookShopController {
    private BookShop bs = new BookShop();

    public void readFromFile  (String SPath, String OSPath, String RSPath) throws IllegalArgumentException {
	Parser parser = new Parser();
	OrderingSystem os = parser.OSReadFromFile(OSPath);
	ReplySystem rs = parser.RSReadFromFile(RSPath);
	Store s = parser.SReadFromFile(SPath, rs);
	bs = new BookShop(s, os, rs);}
	


    public void writeToFile(String SPath, String OSPath, String RSPath) throws IllegalArgumentException{
	Parser parser = new Parser();
	parser.WriteToFile(bs.getOrderingSystem(), OSPath);
	parser.WriteToFile(bs.getStore(), SPath);
	parser.WriteToFile(bs.getReplySystem(), RSPath);
    }

    public void addOrder(Order order) {
	bs.getOrderingSystem().addOrder(order);
    }

    public void cancelOrder(int id) {
	bs.getOrderingSystem().cancelOrder(id);
    }

    public void executeOrder(int id) {
	bs.getOrderingSystem().executeOrder(id, bs.getStore());
    }

    public String showOrders() {
	return bs.getOrderingSystem().toString();
    }

    public String showReplies() {
	return bs.getReplySystem().toString();
    }

    public String showStore() {
	return bs.getStore().toString();
    }

    public String showSumOfExecutedOrders(int startYear, int startMonth,
	    int endYear, int endMonth) {
	return Double.toString(bs.getOrderingSystem().getSumOfExecutedOrders(
		startYear, startMonth, endYear, endMonth));
    }

    public String showExecutedOrders() {
	StringBuilder sb = new StringBuilder();

	bs.getOrderingSystem()
		.getExecutedOrders()
		.forEach(
			(Order order) -> sb.append(order.toString()
				+ System.lineSeparator()));
	return sb.toString();
    }

    public String showNumberOfExecutedOrders(int startYear, int startMonth,
	    int endYear, int endMonth) {
	return Double.toString(bs.getOrderingSystem()
		.getNumberOfExecutedOrders(startYear, startMonth, endYear,
			endMonth));
    }

    public void sortOrdersByDate() {
	bs.getOrderingSystem().sortByDate();

    }

    public void sortOrdersByExecution() {
	bs.getOrderingSystem().sortByExecution();

    }

    public void sortOrdersByPrice() {
	bs.getOrderingSystem().sortByPrice();

    }

    public void addBook(Book book) {

	bs.getStore().addBook(book, bs.getReplySystem());

    }

    public void deleteBook(int id) {

	bs.getStore().deleteBook(id);
    }

    public String showUnclaimedBooks() {
	StringBuilder sb = new StringBuilder();
	bs.getStore()
		.getUnclaimedBooks()
		.forEach(
			(Book book) -> sb.append(book.toString()
				+ System.lineSeparator()));
	return sb.toString();
    }

    public void sortBookByPublicationDate() {
	bs.getStore().sortByPublicationDate();
    }

    public void sortBookByInStore() {
	bs.getStore().sortByInStore();
    }

    public void sortBookByName() {
	bs.getStore().sortByName();
    }

    public void sortBookByPrice() {
	bs.getStore().sortByPrice();
    }

    public void sortRepliesByAlphabet() {
	bs.getReplySystem().sortByAlphabet();
    }

    public void sortRepliesByNumber() {
	bs.getReplySystem().sortByNumber();
    }

    public void addReply(Reply reply) {
	bs.getReplySystem().addReply(reply);

    }

    public void executeReply(int id) {
	bs.getReplySystem().executeReply(bs.getStore(), id);
    }

    public void addBooks(ArrayList<Book> books) {
	bs.getStore().setBooks(books, bs.getReplySystem());
    }

    public void addOrders(ArrayList<Order> orders) {
	bs.getOrderingSystem().setOrders(orders);
    }

    public void addReplies(ArrayList<Reply> replies) {
	bs.getReplySystem().setReplies(replies);
    }

}
