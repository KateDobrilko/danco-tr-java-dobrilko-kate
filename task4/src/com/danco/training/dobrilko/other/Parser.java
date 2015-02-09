package com.danco.training.dobrilko.other;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.danco.training.TextFileWorker;
import com.danco.training.dobrilko.model.Bookshop;
import com.danco.training.dobrilko.model.Store;

public class Parser {

    Store store = new Store();

   /* static Bookshop Parse(String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] fileStrings = tfw.readFromFile();

	int positionOrderingSystem = 0;
	ArrayList<Order> orders = new ArrayList<Order>();
	for (int i = 0; i < fileStrings.length; i++) {
	    if (fileStrings[i].equals("OrderingSystem")) {
		positionOrderingSystem = i;
	    }
	}

	int numberOfOrders = Integer
		.parseInt(fileStrings[positionOrderingSystem++]);
	positionOrderingSystem++;
	for (int i = 0; i < numberOfOrders; i++) {
	    ArrayList<BookType> bookTypes = new ArrayList<BookType>();
	    ArrayList<String> orderStrings = new ArrayList<String>();
	    ArrayList<String> bookTypesStrings = new ArrayList<String>();
	    for (int j = 0; j < 6; j++, positionOrderingSystem++) {
		orderStrings.add(fileStrings[positionOrderingSystem]);
	    }
	    positionOrderingSystem++;
	    for (int j = 0; j < Integer.parseInt(orderStrings.get(5)); j++, positionOrderingSystem++) {
		
		bookTypesStrings.add(fileStrings[positionOrderingSystem]);
		if (bookTypesStrings.get(j).split("|")[0].equals("BookType")) {
		    bookTypes.add(new BookType(bookTypesStrings.get(j).split(
			    "|")[1], bookTypesStrings.get(j).split("|")[2],
			    Double.parseDouble(bookTypesStrings.get(j).split(
				    "|")[3])));

		}
		if (bookTypesStrings.get(j).split("|")[0]
			.equals("BookExemplar")) {
		    BookExemplar be  = new BookExemplar();
		    be.setName(bookTypesStrings.get(j).split("|")[1]);
		    be.setAuthor(bookTypesStrings.get(j).split("|")[2]);
		    be.setPrice(Double.parseDouble(bookTypesStrings.get(j)
			    .split("|")[3]));
		    Calendar c = Calendar.getInstance();
		    c.set(Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[4]
				    .split("/")[2]), Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[4]
				    .split("/")[1]), Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[4]
				    .split("/")[0]));
		    be.setDateOfAddition(c);
		    c.set(Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[5]
				    .split("/")[2]), Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[5]
				    .split("/")[1]), Integer
			    .parseInt(bookTypesStrings.get(j).split("|")[5]
				    .split("/")[0]));
		    be.setDateOfPublication(c);
		    be.setPrice((Double.parseDouble(bookTypesStrings.get(j)
			    .split("|")[6])));
		    bookTypes.add(be);

		}

	    }

	}

    }*/

    public static void WriteToFile(Bookshop bookshop, String path) {
	TextFileWorker tfw = new TextFileWorker(path);
	String[] mas = { bookshop.toString() };
	tfw.writeToFile(mas);
    }
}
