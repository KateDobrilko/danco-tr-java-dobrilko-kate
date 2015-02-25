package com.danco.training.dobrilko.ioutil;

import java.util.Calendar;

import com.danco.training.dobrilko.bookshop.model.Book;

public class SpecificIOFeaturesUtil {

	public static Book readBook() {
		IOUtil.print("Name:");
		String name = IOUtil.readString();
		IOUtil.print("Author:");
		String author = IOUtil.readString();
		IOUtil.print("Price:");
		double price = IOUtil.readDouble();
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		int yearOfAddition = Calendar.getInstance().get(Calendar.YEAR);
		int monthOfAddition = Calendar.getInstance().get(Calendar.MONTH);
		int dayOfAddition = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		IOUtil.print("Year Of Publication:");
		int yearOfPublication = IOUtil.readInt();
		IOUtil.print("Month Of Publication:");
		int monthOfPublication = IOUtil.readInt();
		IOUtil.print("Day Of Publication:");
		int dayOfPublication = IOUtil.readInt();
		return new Book(name, author, price, id, yearOfAddition, monthOfAddition, dayOfAddition, yearOfPublication, monthOfPublication, dayOfPublication);

	}

}
