package com.danco.training.dobrilko.ioutil;

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
		IOUtil.print("Year Of Addition:");
		int yearOfAddition = IOUtil.readInt();
		IOUtil.print("Month Of Addition:");
		int monthOfAddition = IOUtil.readInt();
		IOUtil.print("Day Of Addition:");
		int dayOfAddition = IOUtil.readInt();
		IOUtil.print("Year Of Publication:");
		int yearOfPublication = IOUtil.readInt();
		IOUtil.print("Month Of Publication:");
		int monthOfPublication = IOUtil.readInt();
		IOUtil.print("Day Of Publication:");
		int dayOfPublication = IOUtil.readInt();
		return new Book(name, author, price, id, yearOfAddition, monthOfAddition, dayOfAddition, yearOfPublication, monthOfPublication, dayOfPublication);

	}

}
