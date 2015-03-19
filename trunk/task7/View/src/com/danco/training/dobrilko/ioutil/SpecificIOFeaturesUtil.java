package com.danco.training.dobrilko.ioutil;

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
		IOUtil.print("Id:");
		int id = IOUtil.readInt();
		Date dop = SpecificIOFeaturesUtil.readDate();
		Date cur = new Date();
		cur.setMonth(cur.getMonth() + 1);
		cur.setYear(cur.getYear() + 1900);
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(";");
		sb.append(author);
		sb.append(";");
		sb.append(Double.toString(price));
		sb.append(";");
		sb.append(Integer.toString(id));
		sb.append(";");
		sb.append(Integer.toString(cur.getYear()));
		sb.append(";");
		sb.append(Integer.toString(cur.getMonth()));
		sb.append(";");
		sb.append(Integer.toString(cur.getDate()));
		sb.append(";");
		sb.append(Integer.toString(dop.getYear()));
		sb.append(";");
		sb.append(Integer.toString(dop.getMonth()));
		sb.append(";");
		sb.append(Integer.toString(dop.getDate()));
		return sb.toString();

	}

	@SuppressWarnings("deprecation")
	public static String readOrder() throws InputMismatchException {
		StringBuilder sb = new StringBuilder();
		IOUtil.print("Order id:");
		int id = IOUtil.readInt();
		IOUtil.print("Number of books:");
		int num = IOUtil.readInt();

		IOUtil.print("Executed?:");
		boolean status = IOUtil.readBoolean();

		if (status == true) {
			Date date = readDate();
			sb.append(id);
			sb.append(";");
			sb.append(num);
			sb.append(";");
			sb.append("true");
			sb.append(";");
			sb.append(date.getYear());
			sb.append(";");
			sb.append(date.getMonth());
			sb.append(";");
			sb.append(date.getDate());
			sb.append(System.lineSeparator());
			for (int i = 0; i < num; i++) {
				IOUtil.print("Book #" + i + " id:");
				sb.append(IOUtil.readInt());
				sb.append(";");
				sb.append(System.lineSeparator());
			}

		} else {
			sb.append(id);
			sb.append(";");
			sb.append(num);
			sb.append(";");
			sb.append("false");
			sb.append(";");
			sb.append(System.lineSeparator());
			for (int i = 0; i < num; i++) {
				IOUtil.print("Book #" + i + " id:");
				sb.append(IOUtil.readInt());
				sb.append(";");
				sb.append(System.lineSeparator());
			}
		}
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
		if (executed == true) {
			sb.append("true");
		} else {
			sb.append("false");
		}
		sb.append(System.lineSeparator());
		sb.append(readBook());
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
