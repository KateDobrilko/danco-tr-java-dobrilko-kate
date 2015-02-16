package com.danco.training.dobrilko.task4.other;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;
import com.danco.training.dobrilko.task4.model.Book;
import com.danco.training.dobrilko.task4.model.Order;
import com.danco.training.dobrilko.task4.model.Reply;
import com.danco.training.dobrilko.task4.service.OrderingSystem;
import com.danco.training.dobrilko.task4.service.ReplySystem;
import com.danco.training.dobrilko.task4.service.Store;

public class Parser {
    public void WriteToFile(OrderingSystem os, String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] mas = { os.toString() };
	tfw.writeToFile(mas);
    }

    public void WriteToFile(ReplySystem rs, String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] mas = { rs.toString() };
	tfw.writeToFile(mas);
    }

    public void WriteToFile(Store s, String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] mas = { s.toString() };
	tfw.writeToFile(mas);
    }

    public OrderingSystem OSReadFromFile(String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	OrderingSystem os = new OrderingSystem();
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
		date = new GregorianCalendar( 0,0, 0);
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
	return os;
    }

    public ReplySystem RSReadFromFile(String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	ReplySystem rs = new ReplySystem();
	int currentPointerPosition = 1;
	int numberOfReplies = Integer.parseInt(strings[0]);

	for (int j = 0; j < numberOfReplies; j++) {

	    String[] bookDescription = strings[currentPointerPosition]
		    .split(";");
	    currentPointerPosition++;
	    if (bookDescription[4].equals(" ")) {

		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]),
			Integer.parseInt(bookDescription[7]));
		rs.addReply(new Reply(new Book(bookDescription[0],
			bookDescription[1], Double
				.parseDouble(bookDescription[2]), c, Integer
				.parseInt(bookDescription[3])), Integer
			.parseInt(bookDescription[10])));
	    } else {
		Calendar c1 = Calendar.getInstance();
		c1.set(Integer.parseInt(bookDescription[9]),
			Integer.parseInt(bookDescription[8]),
			Integer.parseInt(bookDescription[7]));

		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt(bookDescription[6]),
			Integer.parseInt(bookDescription[5]),
			Integer.parseInt(bookDescription[4]));
		rs.addReply(new Reply(new Book(bookDescription[0],
			bookDescription[1], Double
				.parseDouble(bookDescription[2]), Integer
				.parseInt(bookDescription[3]), c2
				.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2
				.get(Calendar.DATE), c1.get(Calendar.YEAR), c1
				.get(Calendar.MONTH), c1.get(Calendar.DATE)),
			Integer.parseInt(bookDescription[10])));

	    }
	}
	return rs;
    }

    public Store SReadFromFile(String path, ReplySystem rs) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] strings = tfw.readFromFile();
	Store s = new Store();
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
	return s;
    }

}
