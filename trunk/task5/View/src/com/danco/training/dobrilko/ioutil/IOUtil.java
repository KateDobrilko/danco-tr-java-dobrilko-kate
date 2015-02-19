package com.danco.training.dobrilko.ioutil;

import java.util.GregorianCalendar;
import java.util.Scanner;

import com.danco.training.dobrilko.bookshop.model.Book;

public class IOUtil {

    @SuppressWarnings("resource")
    public static int readInt() {
	Scanner in = new Scanner(System.in);
	return in.nextInt();
    }

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
	return new Book(name, author, price, id, yearOfAddition,
		monthOfAddition, dayOfAddition, yearOfPublication,
		monthOfPublication, dayOfPublication);

    }

    @SuppressWarnings("resource")
    public static double readDouble() {
	Scanner in = new Scanner(System.in);
	return in.nextDouble();
    }

    @SuppressWarnings("resource")
    public static String readString() {
	Scanner in = new Scanner(System.in);
	return in.nextLine();
    }

    @SuppressWarnings("resource")
    public static GregorianCalendar readDate() {
	Scanner in = new Scanner(System.in);
	System.out.println("dd/mm/yy");
	String date = in.nextLine();
	GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(date
		.split("/")[0]), Integer.parseInt(date.split("/")[1]),
		Integer.parseInt(date.split("/")[2]));
	return gc;
    }

    public static void print(String s) {
	System.out.println(s);
    }
}
