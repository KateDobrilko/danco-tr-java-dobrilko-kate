import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
    private int id;
    private ArrayList<BookType> books = new ArrayList<BookType>();
    private Calendar date;
    private boolean executed;
    private Customer customer;

    public Order(int id, ArrayList<BookType> bookOrder, Calendar date,
	    Customer customer) {
	this.id = id;
	this.books = bookOrder;
	this.date = date;
	this.customer = customer;
	this.setExecuted(false);
    }

    public int getId() {
	return id;
    }

    public ArrayList<BookType> getBookOrder() {
	return books;
    }
    
    

    public Calendar getDate() {
	return date;
    }

    public Customer getCustomer() {
	return customer;
    }

    public boolean isExecuted() {
	return executed;
    }

    public void setExecuted(boolean executed) {
	this.executed = executed;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("#");
	sb.append(id);
	sb.append(" | ");
	sb.append("customer: ");
	sb.append(customer.toString());
	sb.append(" | ");
	for(BookType bookType: books){
	    sb.append(bookType.toString() + System.lineSeparator());
	    if(bookType instanceof BookExemplar){sb.append(((BookExemplar)bookType).toString() + System.lineSeparator());}
	}
	
	sb.append("customer: ");
	sb.append(date.toString());
	sb.append(" | ");
	sb.append("executed: ");
	sb.append(Boolean.toString(executed));
	return sb.toString();
    }

}
