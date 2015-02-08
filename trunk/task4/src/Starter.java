import java.util.ArrayList;
import java.util.Calendar;

public class Starter {
    public static void main(String[] args){
    Store store = new Store();
    OrderingSystem orderingSystem= new OrderingSystem();
    ReplySystem replySystem = new ReplySystem();
    Cashier cashier = new Cashier(1234.2);
    CustomerBase customerBase = new CustomerBase();
    Calendar addition  = Calendar.getInstance();
    addition.set(2014, 3, 21);
    Calendar publication = Calendar.getInstance();
    publication.set(1995, 4, 21);
    store.addBookExemplar(new BookExemplar(123,addition, publication, "The Artefacts of Power","Maggie Furey",12.3));
    store.addBookExemplar(new BookExemplar(123,addition, publication, "Arafel","C. J. Cherryh",5.3));
    ArrayList<BookType> books = new ArrayList<BookType>();
    books.add(new BookType("Chronicles of Brothers","Wendy Alec",5.6));
    orderingSystem.addOrder(new Order(12345,books,addition,new Customer(454654)));
    orderingSystem.addOrder(new Order(15454,books,addition,new Customer(4545654)));
    replySystem.addReply(new Reply(new BookType("Cold Tom", "Sally Prue", 12.4)));
    customerBase.addCustomer(new Customer(56546));
    Bookshop bookshop = new Bookshop(store,cashier,orderingSystem,replySystem,customerBase);
    Parser.WriteToFile(bookshop, "new.txt");
    
}
}
