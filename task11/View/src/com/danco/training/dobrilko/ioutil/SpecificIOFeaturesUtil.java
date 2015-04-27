package com.danco.training.dobrilko.ioutil;

import java.util.Date;
import java.util.InputMismatchException;

public class SpecificIOFeaturesUtil {
	@SuppressWarnings("deprecation")
	public static String readBook() throws InputMismatchException {
		IOUtil.print("Id:");
		Integer id = IOUtil.readInt();
		IOUtil.print("Name:");
		String name = IOUtil.readString();
		IOUtil.print("Author:");
		String author = IOUtil.readString();
		IOUtil.print("Ordered?");
		Boolean ordered = IOUtil.readBoolean();
		IOUtil.print("Price:");
		double price = IOUtil.readDouble();
		Date dop = SpecificIOFeaturesUtil.readDate();
		Date doa = new Date();
		IOUtil.print("Order Id:");
		Integer orderId = IOUtil.readInt();
		doa.setMonth(doa.getMonth() + 1);
		doa.setYear(doa.getYear() + 1900);
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(id));
		sb.append(";");
		sb.append(name);
		sb.append(";");
		sb.append(author);
		sb.append(";");
        sb.append(ordered);
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
		sb.append(";");
		sb.append(Double.toString(price));
		sb.append(";");
		sb.append(Integer.toString(orderId));
		return sb.toString();

	}

	@SuppressWarnings("deprecation")
	public static String readOrder() throws InputMismatchException {
		StringBuilder sb = new StringBuilder();
		IOUtil.print("Order id:");
		int id = IOUtil.readInt();
		IOUtil.print("Executed?:");
		boolean status = IOUtil.readBoolean();
		IOUtil.print("Sum:");
		double sum = IOUtil.readDouble();

		Date date = readDate();
		sb.append(id);
		sb.append(";");
		sb.append(date.getYear());
		sb.append(";");
		sb.append(date.getMonth());
		sb.append(";");
		sb.append(date.getDate());
		sb.append(status);
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
		sb.append(Integer.toString(number));
		sb.append(";");
		sb.append(Integer.toString(id));
		sb.append(";");
		sb.append(executed);
		sb.append(";");
		sb.append(readBook());
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
