package com.danco.training.dobrilko.other;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

import com.danco.training.bookshop.Bookshop;
import com.danco.training.dobrilko.entitiy.Book;
import com.danco.training.dobrilko.entitiy.Order;
import com.danco.training.dobrilko.entitiy.Reply;

public class CSVImportExportUtil {
	private static Logger logger = Logger.getLogger(CSVImportExportUtil.class);

	@SuppressWarnings("deprecation")
	public static void ExportOrder(Order order, String path) {

		try {
			FileWriter writer = new FileWriter(path, true);
			StringBuilder sb = new StringBuilder();

			sb.append(Integer.toString(order.getId()));
			sb.append(";");
			sb.append(Integer.toString(order.getBooks().size()));
			sb.append(";");
			if (!(order.getDateOfExecution() == null)) {
				sb.append(Integer
						.toString(order.getDateOfExecution().getDate()));
				sb.append(";");
				sb.append(Integer.toString(order.getDateOfExecution()
						.getMonth()));
				sb.append(";");
				sb.append(Integer
						.toString(order.getDateOfExecution().getYear()));
				sb.append(";");
			}
			sb.append(System.lineSeparator());
			for (Book book : order.getBooks()) {
				sb.append(Integer.toString(book.getId()));
				sb.append(";");
				sb.append(book.getName());
				sb.append(";");
				sb.append(book.getAuthor());
				sb.append(";");
				sb.append(Double.toString(book.getPrice()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfPublication()
						.getDate()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfPublication()
						.getMonth()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfPublication()
						.getYear()));
				sb.append(";");
				if (!book.getDateOfAddition().equals(null)) {

					sb.append(Integer.toString(book.getDateOfAddition()
							.getDate()));
					sb.append(";");
					sb.append(Integer.toString(book.getDateOfAddition()
							.getMonth()));
					sb.append(";");
					sb.append(Integer.toString(book.getDateOfAddition()
							.getYear()));
					sb.append(";");
				}

			}
			sb.append(System.lineSeparator());

			writer.append(sb.toString());
			writer.flush();
			writer.close();

		} catch (IllegalArgumentException e) {
			logger.error("File Not Found. Check your input,", e);
		} catch (IOException e) {
			Logger logger = Logger.getLogger(CSVImportExportUtil.class);
			logger.error("IOException!!!");
		}
	}

	@SuppressWarnings("deprecation")
	public static void ExportReply(Reply reply, String path) {

		try {
			FileWriter writer = new FileWriter(path, true);
			StringBuilder sb = new StringBuilder();
			Book book = reply.getBook();
			sb.append(Integer.toString(reply.getId()));
			sb.append(";");
			sb.append(Integer.toString(reply.getNumberOfRequests()));
			sb.append(";");
			sb.append(Integer.toString(book.getId()));
			sb.append(";");
			sb.append(book.getName());
			sb.append(";");
			sb.append(book.getAuthor());
			sb.append(";");
			sb.append(Double.toString(book.getPrice()));
			sb.append(";");
			if (reply.isExecuted()) {
				sb.append("executed");
			} else {
				sb.append("not executed");
			}
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getDate()));
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getMonth()));
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getYear()));
			sb.append(";");
			if (!book.getDateOfAddition().equals(null)) {

				sb.append(Integer.toString(book.getDateOfAddition().getDate()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfAddition().getMonth()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfAddition().getYear()));
				sb.append(";");
			}

			sb.append(System.lineSeparator());
			writer.append(sb.toString());
			writer.flush();
			writer.close();
		} catch (IllegalArgumentException | IOException e) {
			logger.error("File Not Found. Check your input,", e);
		}

	}

	@SuppressWarnings("deprecation")
	public static void ExportBook(Book book, String path) {
		try {
			FileWriter writer = new FileWriter(path, true);
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.toString(book.getId()));
			sb.append(";");
			sb.append(book.getName());
			sb.append(";");
			sb.append(book.getAuthor());
			sb.append(";");
			sb.append(Double.toString(book.getPrice()));
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getDate()));
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getMonth()));
			sb.append(";");
			sb.append(Integer.toString(book.getDateOfPublication().getYear()));
			sb.append(";");
			if (!book.getDateOfAddition().equals(null)) {

				sb.append(Integer.toString(book.getDateOfAddition().getDate()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfAddition().getMonth()));
				sb.append(";");
				sb.append(Integer.toString(book.getDateOfAddition().getYear()));
				sb.append(";");
			}

			sb.append(System.lineSeparator());
			writer.append(sb.toString());
			writer.flush();
			writer.close();

		} catch (IllegalArgumentException e) {
			logger.error("File Not Found. Check your input,", e);
		} catch (IOException e) {
			logger.error("IOException catched!");
		}
	}

	@SuppressWarnings("deprecation")
	public static void ImportOrders(String csvPath)
			throws IllegalArgumentException {

		BufferedReader br = null;
		String line = "";
		ArrayList<String> strings = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				strings.add(line);

			}

		} catch (FileNotFoundException e) {
			logger.error("File Not Found. Check your input,", e);
		} catch (IOException e) {
			logger.error("IOException catched!");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IOException catched!");
				}
			}
		}

		try {

			int currentPointerPosition = 0;

			while (currentPointerPosition < strings.size()) {

				Date date = new Date();
				String[] infoLine = strings.get(currentPointerPosition).split(
						";");
				currentPointerPosition++;
				int id = Integer.parseInt(infoLine[0]);
				int numberOfBooks = Integer.parseInt(infoLine[1]);
				ArrayList<Book> books = new ArrayList<Book>();
				if (infoLine.length < 3) {
					date = null;
				} else {
					date.setYear(Integer.parseInt(infoLine[4]));
					date.setMonth(Integer.parseInt(infoLine[3]));
					date.setDate(Integer.parseInt(infoLine[2]));

				}

				if (date == null) {

					for (int j = 0; j < numberOfBooks; j++) {
						String[] bookDescription = strings.get(
								currentPointerPosition).split(";");
						currentPointerPosition++;
						int bookId = Integer.parseInt(bookDescription[0]);
						books.add(Bookshop.getInstance().getBookBase()
								.getById(bookId));
					}
					Bookshop.getInstance().getOrderBase()
							.add(new Order(id, books, true, null));
				} else {

					for (int j = 0; j < numberOfBooks; j++) {
						String[] bookDescription = strings.get(
								currentPointerPosition).split(";");
						currentPointerPosition++;

						Date dateOfAddition = new Date(
								Integer.parseInt(bookDescription[10]),
								Integer.parseInt(bookDescription[9]),
								Integer.parseInt(bookDescription[8]));

						Date dateOfPublication = new Date(
								Integer.parseInt(bookDescription[7]),
								Integer.parseInt(bookDescription[6]),
								Integer.parseInt(bookDescription[5]));
						int bookId = Integer.parseInt(bookDescription[0]);
						String name = bookDescription[1];
						String author = bookDescription[2];
						boolean ordered = bookDescription[3].equals("ordered");
						double price = Double.parseDouble(bookDescription[4]);
						books.add(new Book(name, author, price, bookId,
								dateOfAddition, dateOfPublication, ordered));
					}
					Bookshop.getInstance().getOrderBase()
							.add(new Order(id, books, false, date));
				}
			}

		} catch (IllegalArgumentException e) {
			logger.error("File not found. Check your input.", e);
		}

	}

	@SuppressWarnings("deprecation")
	public static void ImportReplies(String csvPath)
			throws IllegalArgumentException {
		BufferedReader br = null;
		String line = "";
		ArrayList<String> strings = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				strings.add(line);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {

			int currentPointerPosition = 0;
			while (currentPointerPosition < strings.size()) {
				{

					String[] replyDescription = strings.get(
							currentPointerPosition).split(";");
					currentPointerPosition++;
					if (replyDescription.length <= 10) {

						Date dateOfPublication = new Date(
								Integer.parseInt(replyDescription[9]),
								Integer.parseInt(replyDescription[8]),
								Integer.parseInt(replyDescription[7]));
						boolean ex = false;
						String executed = replyDescription[6];
						if (executed.equals("executed")) {
							ex = true;
						}
						Bookshop.getInstance()
								.getReplyBase()
								.add(new Reply(
										new Book(
												replyDescription[3],
												replyDescription[4],
												Double.parseDouble(replyDescription[5]),
												Integer.parseInt(replyDescription[2]),
												null, dateOfPublication, false),
										Integer.parseInt(replyDescription[1]),
										Integer.parseInt(replyDescription[0]),
										ex));
					} else {
						Date dateOfAddition = new Date(
								Integer.parseInt(replyDescription[12]),
								Integer.parseInt(replyDescription[11]),
								Integer.parseInt(replyDescription[10]));

						Date dateOfPublication = new Date(
								Integer.parseInt(replyDescription[9]),
								Integer.parseInt(replyDescription[8]),
								Integer.parseInt(replyDescription[7]));
						boolean ex = false;
						String executed = replyDescription[6];
						if (executed.equals("executed")) {
							ex = true;
						}
						Bookshop.getInstance()
								.getReplyBase()
								.add(new Reply(
										new Book(
												replyDescription[3],
												replyDescription[4],
												Double.parseDouble(replyDescription[5]),
												Integer.parseInt(replyDescription[2]),
												dateOfAddition,
												dateOfPublication, false),
										Integer.parseInt(replyDescription[1]),
										Integer.parseInt(replyDescription[0]),
										ex));

					}
				}

			}
		} catch (IllegalArgumentException e) {
			logger.error("File not found. Check your input.", e);
		}

	}

	@SuppressWarnings("deprecation")
	public static void ImportBooks(String csvPath) {
		BufferedReader br = null;
		String line = "";
		ArrayList<String> strings = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				strings.add(line);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {

			int currentPointerPosition = 0;

			while (currentPointerPosition < strings.size()) {
				{

					String[] bookDescription = strings.get(
							currentPointerPosition).split(";");
					currentPointerPosition++;

					Date dateOfAddition = new Date(
							Integer.parseInt(bookDescription[10]),
							Integer.parseInt(bookDescription[9]),
							Integer.parseInt(bookDescription[8]));

					Date dateOfPublication = new Date(
							Integer.parseInt(bookDescription[7]),
							Integer.parseInt(bookDescription[6]),
							Integer.parseInt(bookDescription[5]));
					int id = Integer.parseInt(bookDescription[0]);
					String name = bookDescription[1];
					String author = bookDescription[2];
					boolean ordered = bookDescription[3].equals("ordered");
					double price = Double.parseDouble(bookDescription[4]);
					Bookshop.getInstance()
							.getBookBase()
							.add(new Book(name, author, price, id,
									dateOfAddition, dateOfPublication, ordered));

				}
			}
		} catch (IllegalArgumentException e) {
			logger.error("File not found. Check your input.", e);
		}

	}
}
