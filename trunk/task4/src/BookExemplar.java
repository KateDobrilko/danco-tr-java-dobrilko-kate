import java.util.Calendar;

public class BookExemplar extends BookType {
    private int id;
    private Calendar dateOfAddition;
    private Calendar dateOfPublication;
    private boolean unclaimed;

    public BookExemplar() {

    }

    public BookExemplar(int id, Calendar dateOfAddition,
	    Calendar dateOfPublication, String name, String author,
	    double price, boolean inStore) {
	super(name, author, price);
	this.id = id;
	this.dateOfAddition = dateOfAddition;
	this.dateOfPublication = dateOfPublication;
	Calendar c = Calendar.getInstance();

	int currentMonth = c.get(Calendar.MONTH);

	int additionMonth = this.getDateOfAddition().get(Calendar.MONTH);

	boolean condition = (currentMonth - additionMonth) > 6;

	if (condition) {
	    this.unclaimed = true;
	} else {
	    this.unclaimed = false;
	}
    }

    public Calendar getDateOfAddition() {
	return dateOfAddition;
    }

    public Calendar getDateOfPublication() {
	return dateOfPublication;
    }

    public int getId() {
	return id;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("#");
	sb.append(id);
	sb.append(" | ");
	sb.append("'");
	sb.append(name);
	sb.append("'");
	sb.append(", ");
	sb.append(author);
	sb.append(" | ");
	sb.append("date of addition: ");
	sb.append(dateOfAddition.getTime().toString());
	sb.append(" | ");
	sb.append("date of publication: ");
	sb.append(dateOfPublication.getTime().toString());
	sb.append(" | ");
	sb.append("price: ");
	sb.append(Double.toString(price));
	sb.append("$");
	return sb.toString();
    }

    public boolean isUnclaimed() {
	return unclaimed;
    }

    public void setDateOfAddition(Calendar dateOfAddition) {
	this.dateOfAddition = dateOfAddition;
    }

}
