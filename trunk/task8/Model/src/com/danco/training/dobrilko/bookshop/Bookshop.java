package com.danco.training.dobrilko.bookshop;

import java.io.Serializable;

import com.danco.training.dobrilko.database.BookBase;
import com.danco.training.dobrilko.database.OrderBase;
import com.danco.training.dobrilko.database.ReplyBase;

public class Bookshop implements Serializable {
	
	private static final long serialVersionUID = -2351767735674940103L;
	private static Bookshop instance;
	private BookBase bookBase;
	private OrderBase orderBase;
	private ReplyBase replyBase;

	private Bookshop() {
		this.setReplyBase(new ReplyBase());
		this.setOrderBase(new OrderBase());
		this.setBookBase(new BookBase());
	}

	public static Bookshop getInstance() {
		if (instance == null) {
			instance = new Bookshop();
		}

		return instance;

	}

	public BookBase getBookBase() {
		return bookBase;
	}

	public void setBookBase(BookBase bookBase) {
		this.bookBase = bookBase;
	}

	public OrderBase getOrderBase() {
		return orderBase;
	}

	public void setOrderBase(OrderBase orderBase) {
		this.orderBase = orderBase;
	}

	public ReplyBase getReplyBase() {
		return replyBase;
	}

	public void setReplyBase(ReplyBase replyBase) {
		this.replyBase = replyBase;
	}

}
