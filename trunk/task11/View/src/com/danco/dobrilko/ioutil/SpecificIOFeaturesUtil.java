package com.danco.dobrilko.ioutil;

import java.util.Date;
import java.util.InputMismatchException;

public class SpecificIOFeaturesUtil {
	@SuppressWarnings("deprecation")
	public static String readBook() throws InputMismatchException {

		IOUtil.print("Name:");
		String name = IOUtil.readString();
		IOUtil.print("Author:");
		String author = IOUtil.readString();
		IOUtil.print("Price:");
		double price = IOUtil.readDouble();
		IOUtil.print("Number Of Exemplars:");
		Integer numberOfExemplars = IOUtil.readInt();
		IOUtil.print("Date Of Arrival:");
		Date dop = SpecificIOFeaturesUtil.readDate();
		Date doa = new Date();
		doa.setMonth(doa.getMonth() + 1);
		doa.setYear(doa.getYear() + 1900);
		StringBuilder sb = new StringBuilder();
		sb.append(readId());
		sb.append(";");
		sb.append(name);
		sb.append(";");
		sb.append(author);
		sb.append(";");
		sb.append(numberOfExemplars);
		sb.append(";");
		sb.append(Double.toString(price));
		sb.append(";");
		sb.append(Integer.toString(doa.getYear()));
		sb.append(";");
		sb.append(Integer.toString(doa.getMonth()));
		sb.append(";");
		sb.append(Integer.toString(doa.getDate()));
		sb.append(";");
		sb.append(Integer.toString(dop.getYear()));
		sb.append(";");
		sb.append(Integer.toString(dop.getMonth()));
		sb.append(";");
		sb.append(Integer.toString(dop.getDate()));

		return sb.toString();

	}

	public static String readOrder() throws InputMismatchException {
		StringBuilder sb = new StringBuilder();
		IOUtil.print("Order id:");
		int id = IOUtil.readInt();
		IOUtil.print("Sum:");
		double sum = IOUtil.readDouble();

		sb.append(id);
		sb.append(";");
		sb.append(sum);
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	public static String readReply() throws InputMismatchException {
		StringBuilder sb = new StringBuilder();
		IOUtil.print("Number of replies:");
		int number = IOUtil.readInt();
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		IOUtil.print("Executed?");
		boolean executed = IOUtil.readBoolean();
		IOUtil.print("Book Id?");
		int bookId = IOUtil.readInt();
		sb.append(Integer.toString(number));
		sb.append(";");
		sb.append(Integer.toString(id));
		sb.append(";");
		sb.append(executed);
		sb.append(";");
		sb.append(bookId);
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	public static Date readDate() throws InputMismatchException {
		IOUtil.print("Input date (y,m,d):");

		return new Date(IOUtil.readInt(), IOUtil.readInt(), IOUtil.readInt());
	}

	public static int readId() throws InputMismatchException {
		IOUtil.print("Id:");
		return IOUtil.readInt();
	}
}
