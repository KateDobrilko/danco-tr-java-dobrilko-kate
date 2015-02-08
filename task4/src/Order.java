import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String id;
    private ArrayList<BookExemplar> books;
    private Date date;
    private boolean executed;
    private Customer customer;

    public Order(String id, ArrayList<BookExemplar> books, Date date,
	    Customer customer) {
	this.id = id;
	this.books = books;
	this.date = date;
	this.customer = customer;
	this.setExecuted(false);
    }

    public String getId() {
	return id;
    }

    public ArrayList<BookExemplar> getBooks() {
	return books;
    }

    public Date getDate() {
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
	books.forEach((BookExemplar value) -> sb.append(value.toString()
		+ System.lineSeparator()));
	sb.append("customer: ");
	sb.append(date.toString());
	sb.append(" | ");
	sb.append("executed: ");
	sb.append(Boolean.toString(executed));
	return sb.toString();
    }

}
