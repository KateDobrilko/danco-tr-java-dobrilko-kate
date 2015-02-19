package com.danco.training.dobrilko.bookshop.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.bookshop.model.Book;
import com.danco.training.dobrilko.bookshop.model.Order;
import com.danco.training.dobrilko.bookshop.model.Reply;
import com.danco.training.dobrilko.bookshop.other.Parser;
import com.danco.training.dobrilko.bookshop.service.BookShop;
import com.danco.training.dobrilko.bookshop.service.OrderingSystem;
import com.danco.training.dobrilko.bookshop.service.ReplySystem;
import com.danco.training.dobrilko.bookshop.service.Store;

public class Main {
    
   /* static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
	try {

	    final String ORDERING_SYSTEM_PATH_READ = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\OrderingSystem.txt";
	    final String REPLY_SYSTEM_PATH_READ = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\ReplySystem.txt";
	    final String STORE_PATH_READ = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\Store.txt";
	    final String ORDERING_SYSTEM_PATH_WRITE = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\OrderingSystem.txt";
	    final String REPLY_SYSTEM_PATH_WRITE = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\ReplySystem.txt";
	    final String STORE_PATH_WRITE = "src\\com\\danco\\training\\dobrilko\\bookshop\\txt\\Store.txt";
	    Parser parser = new Parser();
	    OrderingSystem os = parser
		    .OSReadFromFile(ORDERING_SYSTEM_PATH_READ);
	    ReplySystem rs = parser.RSReadFromFile(REPLY_SYSTEM_PATH_READ);
	    Store s = parser.SReadFromFile(STORE_PATH_READ, rs);
	    BookShop bs = new BookShop(s, os, rs);
	    parser.WriteToFile(os, ORDERING_SYSTEM_PATH_WRITE);
	    parser.WriteToFile(rs, REPLY_SYSTEM_PATH_WRITE);
	    parser.WriteToFile(s, STORE_PATH_WRITE);

	    System.out.println("Bookshop read from file test:");
	    System.out.println(bs.toString());
	    System.out.println("Store functions test: ");
	    System.out.println("___ Unclaimed Books:");
	    bs.getStore()
		    .getUnclaimedBooks()
		    .forEach((Book book) -> System.out.println(book.toString()));
	    System.out.println("___ Unclaimed Books Date Sorting:");
	    bs.getStore().sortByPublicationDate();
	    bs.getStore()
		    .getUnclaimedBooks()
		    .forEach((Book book) -> System.out.println(book.toString()));
	    System.out.println("___ Unclaimed Books Price Sorting:");
	    bs.getStore().sortByPrice();
	    bs.getStore()
		    .getUnclaimedBooks()
		    .forEach((Book book) -> System.out.println(book.toString()));
	    System.out.println("Store Book List sorting test:");
	    System.out.println("___Name Sorting:");
	    bs.getStore().sortByName();
	    System.out.println(bs.getStore().toString());
	    System.out.println("___In Store Sorting:");
	    bs.getStore().sortByInStore();
	    System.out.println(bs.getStore().toString());
	    System.out.println("___Price Sorting:");
	    bs.getStore().sortByPrice();
	    System.out.println(bs.getStore().toString());
	    System.out.println("___Publication Date Sorting:");
	    bs.getStore().sortByPublicationDate();
	    System.out.println(bs.getStore().toString());
	    System.out.println("___Book Addition & Reply Execution test:");
	    Book replyBook = bs.getReplySystem().getReplies().get(0).getBook();
	    Book newBook = new Book(replyBook.getName(), replyBook.getAuthor(),
		    replyBook.getPrice(), replyBook.getId(), Calendar
			    .getInstance().get(Calendar.YEAR), Calendar
			    .getInstance().get(Calendar.MONTH), Calendar
			    .getInstance().get(Calendar.DATE), replyBook
			    .getDateOfPublication().get(Calendar.YEAR),
		    replyBook.getDateOfPublication().get(Calendar.MONTH),
		    replyBook.getDateOfPublication().get(Calendar.DATE));
	    System.out.println("___Store:");
	    System.out.println(bs.getStore().toString());
	    System.out.println("___Replies:");
	    System.out.println(bs.getReplySystem().toString());
	    System.out.println("___Replies status:");
	    bs.getReplySystem()
		    .getReplies()
		    .forEach(
			    (Reply reply) -> System.out.println(Boolean
				    .toString(reply.isExecuted()) + " "));
	    bs.getStore().addBook(newBook, bs.getReplySystem());
	    System.out.println("___Store now:");
	    System.out.println(bs.getStore().toString());
	    System.out.println("___Replies status:");
	    bs.getReplySystem()
		    .getReplies()
		    .forEach(
			    (Reply reply) -> System.out.println(Boolean
				    .toString(reply.isExecuted()) + " "));
	    System.out.println("___Book Deleting:");
	    bs.getStore().deleteBook(bs.getStore().getBooks().get(0).getId());
	    System.out.println(bs.getStore().toString());

	    System.out.println(bs.getReplySystem().toString());
	    System.out.println("___Reply Addition:");
	    Reply r = new Reply(new Book("Anna Karenina", "Tolstoi", 12.5,
		    new GregorianCalendar(2001, 1, 3), 545654),3,453154);
	    bs.getReplySystem().addReply(r);
	    bs.getReplySystem().addReply(r);
	    System.out.println(bs.getReplySystem().toString());
	    System.out.println("___Order Addition:");
	    ArrayList<Book> books = new ArrayList<Book>();
	    books.add(new Book("Kolobok", "People", 55.1,
		    new GregorianCalendar(2010, 3, 2), 54654));
	    bs.getOrderingSystem().addOrder(new Order(100, books));
	    System.out.println(bs.getOrderingSystem().toString());
	    System.out.println("___Order Cancellation:");
	    bs.getOrderingSystem().cancelOrder(100);
	    System.out.println(bs.getOrderingSystem().toString());
	    System.out.println("___Order Execution:");
	    bs.getOrderingSystem().executeOrder(5451, s);
	    System.out.println(bs.getOrderingSystem().toString());
	    System.out.println(bs.getStore().toString());

	    System.out.println("___Sum Of Executed Orders:");
	    System.out.println(bs.getOrderingSystem().getSumOfExecutedOrders(
		    2013, 7, 2014, 5));
	    System.out.println("___ Executed Orders:");
	    bs.getOrderingSystem()
		    .getExecutedOrders(2013, 7, 2014, 5)
		    .forEach(
			    (Order order) -> System.out.println(order
				    .toString()));
	    System.out.println("___ Executed Orders Number:");
	    System.out.println(bs.getOrderingSystem()
		    .getNumberOfExecutedOrders(2013, 7, 2014, 5));
	    System.out.println("___ Executed Orders Date Sorting :");
	    bs.getOrderingSystem().sortByDate();
	    bs.getOrderingSystem()
		    .getExecutedOrders(2013, 7, 2014, 5)
		    .forEach(
			    (Order order) -> System.out.println(order
				    .toString()));
	    System.out.println("___ Executed Orders Price Sorting :");
	    bs.getOrderingSystem().sortByPrice();
	    bs.getOrderingSystem()
		    .getExecutedOrders(2013, 7, 2014, 5)
		    .forEach(
			    (Order order) -> System.out.println(order
				    .toString()));
	    System.out.println("___ Orders Date Sorting :");
	    bs.getOrderingSystem().sortByDate();
	    System.out.println(bs.getOrderingSystem().toString());
	    System.out.println("___ Orders Price Sorting :");
	    bs.getOrderingSystem().sortByPrice();
	    System.out.println(bs.getOrderingSystem().toString());
	    System.out.println("___ Orders Executed Sorting :");
	    bs.getOrderingSystem().sortByExecution();
	    System.out.println(bs.getOrderingSystem().toString());
	} catch (IllegalArgumentException e) {
	    logger.error("Sorry! Check read/write files directory.", e);
	} catch (RuntimeException e) {
	    logger.error(e.getMessage(), e);
	}

    }
*/
}
