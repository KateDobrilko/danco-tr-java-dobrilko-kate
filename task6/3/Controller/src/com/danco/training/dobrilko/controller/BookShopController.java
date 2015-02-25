package com.danco.training.dobrilko.controller;

import java.util.ArrayList;

import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.other.Initializer;
import com.danco.training.dobrilko.bookshop.other.SerializerUtil;
import com.danco.training.dobrilko.bookshop.service.BookShop;

public class BookShopController {
	private static BookShop instance = new BookShop();

	public static BookShop getInstance() {
		return instance;
	}

	public static void readFromFile(String path) {
		SerializerUtil.ReadFromFile(path, instance);
	}

	public static void writeInFile(String path) {
		SerializerUtil.WriteInFile(path, instance);
	}

	public static void initialize() {

		Initializer.Initialize(instance);
	}

	public static void addOrder(Order order) {
		getInstance().getOrderingSystem().addOrder(order);
	}

	public static void cancelOrder(int id) {
		getInstance().getOrderingSystem().cancelOrder(id);
	}

	public static void executeOrder(int id) {
		getInstance().getOrderingSystem().executeOrder(id, getInstance().getStore());
	}

	public static String showOrders() {
		return getInstance().getOrderingSystem().toString();
	}

	public static String showReplies() {
		return getInstance().getReplySystem().toString();
	}

	public static String showStore() {
		return getInstance().getStore().toString();
	}

	public static String showSumOfExecutedOrders(int startYear, int startMonth, int endYear, int endMonth) {
		return Double.toString(getInstance().getOrderingSystem().getSumOfExecutedOrders(startYear, startMonth, endYear, endMonth));
	}

	public static String showExecutedOrders() {
		StringBuilder sb = new StringBuilder();
		getInstance().getOrderingSystem().getExecutedOrders().forEach((Order order) -> sb.append(order.toString() + System.lineSeparator()));
		return sb.toString();
	}

	public static String showNumberOfExecutedOrders(int startYear, int startMonth, int endYear, int endMonth) {
		return Double.toString(getInstance().getOrderingSystem().getNumberOfExecutedOrders(startYear, startMonth, endYear, endMonth));
	}

	public static void sortOrdersByDate() {
		getInstance().getOrderingSystem().sortByDate();

	}

	public static void sortOrdersByExecution() {
		getInstance().getOrderingSystem().sortByExecution();

	}

	public static void sortOrdersByPrice() {
		getInstance().getOrderingSystem().sortByPrice();

	}

	public static void addBook(Book book) {

		getInstance().getStore().addBook(book, getInstance().getReplySystem());

	}

	public static void deleteBook(int id) {

		getInstance().getStore().deleteBook(id);
	}

	public static String showUnclaimedBooks() {
		StringBuilder sb = new StringBuilder();
		getInstance().getStore().getUnclaimedBooks().forEach((Book book) -> sb.append(book.toString() + System.lineSeparator()));
		return sb.toString();
	}

	public static void sortBookByPublicationDate() {
		getInstance().getStore().sortByPublicationDate();
	}

	public static void sortBookByInStore() {
		getInstance().getStore().sortByInStore();
	}

	public static void sortBookByName() {
		getInstance().getStore().sortByName();
	}

	public static void sortBookByPrice() {
		getInstance().getStore().sortByPrice();
	}

	public static void sortRepliesByAlphabet() {
		getInstance().getReplySystem().sortByAlphabet();
	}

	public static void sortRepliesByNumber() {
		getInstance().getReplySystem().sortByNumber();
	}

	public static void addReply(Reply reply) {
		getInstance().getReplySystem().addReply(reply);

	}

	public static void executeReply(int id) {
		getInstance().getReplySystem().executeReply(getInstance().getStore(), id);
	}

	public static void addBooks(ArrayList<Book> books) {
		getInstance().getStore().setBooks(books, getInstance().getReplySystem());
	}

	public static void addOrders(ArrayList<Order> orders) {
		getInstance().getOrderingSystem().setOrders(orders);
	}

	public static void addReplies(ArrayList<Reply> replies) {
		getInstance().getReplySystem().setReplies(replies);
	}

}
