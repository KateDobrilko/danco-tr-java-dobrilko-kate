package com.danco.training.dobrilko.task4.main;
import java.util.ArrayList;
import java.util.Calendar;

import com.danco.training.dobrilko.task4.model.BookExemplar;
import com.danco.training.dobrilko.task4.model.BookType;
import com.danco.training.dobrilko.task4.model.Bookshop;
import com.danco.training.dobrilko.task4.model.Cashier;
import com.danco.training.dobrilko.task4.model.Customer;
import com.danco.training.dobrilko.task4.model.CustomerBase;
import com.danco.training.dobrilko.task4.model.Order;
import com.danco.training.dobrilko.task4.model.OrderingSystem;
import com.danco.training.dobrilko.task4.model.Reply;
import com.danco.training.dobrilko.task4.model.ReplySystem;
import com.danco.training.dobrilko.task4.model.Store;
import com.danco.training.dobrilko.task4.other.Parser;

public class Starter {
    public static void main(String[] args){
    Store store = new Store();
    OrderingSystem orderingSystem= new OrderingSystem();
    ReplySystem replySystem = new ReplySystem();
    Cashier cashier = new Cashier(1234.2);
    CustomerBase customerBase = new CustomerBase();
    Calendar addition1  = Calendar.getInstance();
    addition1.set(2014, 3, 21);
    Calendar publication1 = Calendar.getInstance();
    publication1.set(1995, 4, 21);
    store.addBookExemplar(new BookExemplar(123,addition1, publication1, "The Artefacts of Power","Maggie Furey",12.3));
    Calendar addition2  = Calendar.getInstance();
    Calendar publication2 = Calendar.getInstance();
    addition2.set(2014, 5, 20);
    publication2.set(1998, 4, 11);
    store.addBookExemplar(new BookExemplar(111,addition2, publication2, "Arafel","C. J. Cherryh",5.3));
    Calendar addition3  = Calendar.getInstance();
    Calendar publication3 = Calendar.getInstance();
    addition3.set(2013, 6, 25);
    publication3.set(1999, 5, 11);
    store.addBookExemplar(new BookExemplar(156,addition3, publication3, "Coraline","Neil Gaiman",6.8));
    store.addBookType(new BookType());
    ArrayList<BookType> books = new ArrayList<BookType>();
    books.add(new BookType("Chronicles of Brothers","Wendy Alec",5.6));
    orderingSystem.addOrder(new Order(12345,books,addition1,new Customer(454654)));
    orderingSystem.addOrder(new Order(15454,books,addition2,new Customer(4545654)));
    replySystem.addReply(new Reply(new BookType("Cold Tom", "Sally Prue", 12.4)));
    customerBase.addCustomer(new Customer(56546));
    Bookshop bookshop = new Bookshop(store,cashier,orderingSystem,replySystem,customerBase);
    Parser.WriteToFile(bookshop, "src\\new.txt");
    
}
}
