package com.danco.training.dobrilko.bookshop.other;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.danco.training.TextFileWorker;
import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.service.OrderingSystem;
import com.danco.training.dobrilko.bookshop.service.ReplySystem;
import com.danco.training.dobrilko.bookshop.service.Store;

public class Parser {
    Logger logger = Logger.getLogger(Parser.class);
    public void WriteToFile(OrderingSystem os, String path) {
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	StringBuilder sb = new StringBuilder();
	sb.append(Integer.toString(os.getOrders().size()));
	sb.append(System.lineSeparator());
	for (Order order : os.getOrders()) {
	    sb.append(Integer.toString(order.getId()));
	    sb.append(";");
	    sb.append(Integer.toString(order.getBooks().size()));
	    sb.append(";");
	    if (!(order.getDate().get(Calendar.YEAR) == (2))) {
		sb.append(Integer.toString(order.getDate().get(Calendar.DATE)));
		sb.append(";");
		sb.append(Integer.toString(order.getDate().get(Calendar.MONTH)));
		sb.append(";");
		sb.append(Integer.toString(order.getDate().get(Calendar.YEAR)));
		sb.append(";");
	    } else {
		sb.append(" ");
		sb.append(";");
		sb.append(" ");
		sb.append(";");
		sb.append(" ");
		sb.append(";");
	    }
	    sb.append(System.lineSeparator());
	    for (Book book : order.getBooks()) {
		sb.append(book.getName());
		sb.append(";");
		sb.append(book.getAuthor());
		sb.append(";");
		sb.append(Double.toString(book.getPrice()));
		sb.append(";");
		sb.append(Integer.toString(book.getId()));
		sb.append(";");
		if (book.getDateOfAddition() == null) {
		    sb.append(" ; ; ;");
		} else {
		    sb.append(Integer.toString(book.getDateOfAddition().get(
			    Calendar.DATE)));
		    sb.append(";");
		    sb.append(Integer.toString(book.getDateOfAddition().get(
			    Calendar.MONTH)));
		    sb.append(";");
		    sb.append(Integer.toString(book.getDateOfAddition().get(
			    Calendar.YEAR)));
		    sb.append(";");
		}
		sb.append(Integer.toString(book.getDateOfPublication().get(
			Calendar.DATE)));
		sb.append(";");
		sb.append(Integer.toString(book.getDateOfPublication().get(
			Calendar.MONTH)));
		sb.append(";");
		sb.append(Integer.toString(book.getDateOfPublication().get(
			Calendar.YEAR)));
		sb.append(";");
		sb.append(System.lineSeparator());
	    }

	    String[] mas = { os.toString() };
	    tfw.writeToFile(mas);
	}
	}
	catch(IllegalArgumentException e){
	    logger.error("File Not Found. Check your input,", e);
	}
    }

    public void WriteToFile(ReplySystem rs, String path) {
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	StringBuilder sb = new StringBuilder();
	sb.append(Integer.toString(rs.getReplies().size()));
	sb.append(System.lineSeparator());
	for (Reply reply : rs.getReplies()) {
	    sb.append(Integer.toString(reply.getId()));
	    sb.append(";");
	    sb.append(reply.getBook().getName());
	    sb.append(";");
	    sb.append(reply.getBook().getAuthor());
	    sb.append(";");
	    sb.append(Double.toString(reply.getBook().getPrice()));
	    sb.append(";");
	    sb.append(Integer.toString(reply.getBook().getId()));
	    sb.append(";");
	    if (reply.getBook().getDateOfAddition() == null) {
		sb.append(" ; ; ;");
	    } else {
		sb.append(Integer.toString(reply.getBook().getDateOfAddition()
			.get(Calendar.DATE)));
		sb.append(";");
		sb.append(Integer.toString(reply.getBook().getDateOfAddition()
			.get(Calendar.MONTH)));
		sb.append(";");
		sb.append(Integer.toString(reply.getBook().getDateOfAddition()
			.get(Calendar.YEAR)));
		sb.append(";");
	    }
	    sb.append(Integer.toString(reply.getBook().getDateOfPublication()
		    .get(Calendar.DATE)));
	    sb.append(";");
	    sb.append(Integer.toString(reply.getBook().getDateOfPublication()
		    .get(Calendar.MONTH)));
	    sb.append(";");
	    sb.append(Integer.toString(reply.getBook().getDateOfPublication()
		    .get(Calendar.YEAR)));
	    sb.append(";");
	    sb.append(Integer.toString(reply.getNumberOfRequests()));
	    sb.append(";");
	    sb.append(System.lineSeparator());
	}
	String[] mas = { sb.toString() };
	tfw.writeToFile(mas);}
	catch(IllegalArgumentException e)
	{
	    logger.error("File not found. Check your input.", e);
	}
    }

    public void WriteToFile(Store s, String path) throws IllegalArgumentException{
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	StringBuilder sb = new StringBuilder();
	sb.append(Integer.toString(s.getBooks().size()));
	sb.append(System.lineSeparator());
	for (Book book : s.getBooks()) {

	    sb.append(book.getName());
	    sb.append(";");
	    sb.append(book.getAuthor());
	    sb.append(";");
	    sb.append(Double.toString(book.getPrice()));
	    sb.append(";");
	    sb.append(Integer.toString(book.getId()));
	    sb.append(";");
	    if (book.getDateOfAddition() == null) {
		sb.append(" ; ; ;");
	    } else {
		sb.append(Integer.toString(book.getDateOfAddition().get(
			Calendar.DATE)));
		sb.append(";");
		sb.append(Integer.toString(book.getDateOfAddition().get(
			Calendar.MONTH)));
		sb.append(";");
		sb.append(Integer.toString(book.getDateOfAddition().get(
			Calendar.YEAR)));
		sb.append(";");
	    }
	    sb.append(Integer.toString(book.getDateOfPublication().get(
		    Calendar.DATE)));
	    sb.append(";");
	    sb.append(Integer.toString(book.getDateOfPublication().get(
		    Calendar.MONTH)));
	    sb.append(";");
	    sb.append(Integer.toString(book.getDateOfPublication().get(
		    Calendar.YEAR)));
	    sb.append(";");
	    sb.append(System.lineSeparator());
	}
	String[] mas = { sb.toString() };
	tfw.writeToFile(mas);}
	catch(IllegalArgumentException e){
	    logger.error("File not found. Check your input.", e);
	}
    }

    public OrderingSystem OSReadFromFile(String path) throws IllegalArgumentException {
	OrderingSystem os = new OrderingSystem();
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	
	int currentPointerPosition = 1;
	int numberOfOrders = Integer.parseInt(strings[0]);
	for (int i = 0; i < numberOfOrders; i++) {
	    int id;
	    int numberOfBooks;
	    Calendar date = Calendar.getInstance();
	    String[] infoLine = strings[currentPointerPosition].split(";");
	    currentPointerPosition++;
	    id = Integer.parseInt(infoLine[0]);
	    numberOfBooks = Integer.parseInt(infoLine[1]);
	    if (infoLine[2].equals(" ")) {
		date = new GregorianCalendar(0, 0, 0);
	    } else {
		date.set(Integer.parseInt(infoLine[4]),
			Integer.parseInt(infoLine[3]),
			Integer.parseInt(infoLine[2]));
	    }
	    ArrayList<Book> books = new ArrayList<Book>();
	    for (int j = 0; j < numberOfBooks; j++) {
		String[] bookDescription = strings[currentPointerPosition]
			.split(";");
		currentPointerPosition++;
		if (bookDescription[4].equals(" ")) {

		    Calendar c = Calendar.getInstance();
		    c.set(Integer.parseInt(bookDescription[9]),
			    Integer.parseInt(bookDescription[8]),
			    Integer.parseInt(bookDescription[7]));
		    books.add(new Book(bookDescription[0], bookDescription[1],
			    Double.parseDouble(bookDescription[2]), c, Integer
				    .parseInt(bookDescription[3])));

		} else {
		    Calendar c1 = Calendar.getInstance();
		    c1.set(Integer.parseInt(bookDescription[9]),
			    Integer.parseInt(bookDescription[8]),
			    Integer.parseInt(bookDescription[7]));

		    Calendar c2 = Calendar.getInstance();
		    c2.set(Integer.parseInt(bookDescription[6]),
			    Integer.parseInt(bookDescription[5]),
			    Integer.parseInt(bookDescription[4]));
		    books.add(new Book(bookDescription[0], bookDescription[1],
			    Double.parseDouble(bookDescription[2]), Integer
				    .parseInt(bookDescription[3]), c2
				    .get(Calendar.YEAR),
			    c2.get(Calendar.MONTH), c2.get(Calendar.DATE), c1
				    .get(Calendar.YEAR),
			    c1.get(Calendar.MONTH), c1.get(Calendar.DATE)));

		}

	    }
	    if (date.get(Calendar.YEAR) == (2)) {
		os.addOrder(new Order(id, books));
	    } else {
		os.addOrder(new Order(id, books, date.get(Calendar.DATE), date
			.get(Calendar.MONTH), date.get(Calendar.YEAR)));
	    }
	}
	
	}
	catch(IllegalArgumentException e){
	    logger.error("File not found. Check your input.", e);
	}
	return os;
    }

    public ReplySystem RSReadFromFile(String path) throws IllegalArgumentException {
	ReplySystem rs = new ReplySystem();
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	int currentPointerPosition = 1;
	int numberOfReplies = Integer.parseInt(strings[0]);

	for (int j = 0; j < numberOfReplies; j++) {

	    String[] bookDescription = strings[currentPointerPosition]
		    .split(";");
	    currentPointerPosition++;
	    if (bookDescription[5].equals(" ")) {

		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(bookDescription[10]),
			Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]));
		rs.addReply(new Reply(new Book(bookDescription[1],
			bookDescription[2], Double
				.parseDouble(bookDescription[3]), c, Integer
				.parseInt(bookDescription[4])), Integer
			.parseInt(bookDescription[11]), Integer
			.parseInt(bookDescription[0])));
	    } else {
		Calendar c1 = Calendar.getInstance();
		c1.set(Integer.parseInt(bookDescription[10]),
			Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]));

		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt(bookDescription[7]),
			Integer.parseInt(bookDescription[6]),
			Integer.parseInt(bookDescription[5]));
		rs.addReply(new Reply(new Book(bookDescription[1],
			bookDescription[2], Double
				.parseDouble(bookDescription[3]), Integer
				.parseInt(bookDescription[4]), c2
				.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2
				.get(Calendar.DATE), c1.get(Calendar.YEAR), c1
				.get(Calendar.MONTH), c1.get(Calendar.DATE)),
			Integer.parseInt(bookDescription[11]), Integer
				.parseInt(bookDescription[0])));

	    }
	}
	
	}
	catch(IllegalArgumentException e){
	    logger.error("File not found. Check your input.", e);
	}
	return rs;
    }

    public Store SReadFromFile(String path, ReplySystem rs) {
	Store s = new Store();
	try{
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	
	int currentPointerPosition = 1;
	int numberOfBooks = Integer.parseInt(strings[0]);
	for (int j = 0; j < numberOfBooks; j++) {

	    String[] bookDescription = strings[currentPointerPosition]
		    .split(";");
	    currentPointerPosition++;
	    if (bookDescription[4].equals(" ")) {

		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]),
			Integer.parseInt(bookDescription[7]));
		s.addBook(
			new Book(bookDescription[0], bookDescription[1], Double
				.parseDouble(bookDescription[2]), c, Integer
				.parseInt(bookDescription[3])), rs);
	    } else {
		Calendar c1 = Calendar.getInstance();
		c1.set(Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]),
			Integer.parseInt(bookDescription[7]));

		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt(bookDescription[6]),
			Integer.parseInt(bookDescription[5]),
			Integer.parseInt(bookDescription[4]));
		s.addBook(
			new Book(bookDescription[0], bookDescription[1], Double
				.parseDouble(bookDescription[2]), Integer
				.parseInt(bookDescription[3]), c2
				.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2
				.get(Calendar.DATE), c1.get(Calendar.YEAR), c1
				.get(Calendar.MONTH), c1.get(Calendar.DATE)),
			rs);

	    }
	}
	}
	catch(IllegalArgumentException e){
	    logger.error("File not found. Check your input.", e);
	}
	return s;
    }

}
