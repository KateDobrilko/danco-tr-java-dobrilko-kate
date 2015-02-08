

public class BookType {
   
    protected String name;
    protected String author;
    protected double price;
    protected boolean inStore;
   

    public BookType() {
	
    }

    public BookType( String name, String author, double price) {
	
	this.name = name;
	this.author = author;
	this.price = price;
	this.inStore = false;
	
	
    }

    public String getName() {
	return name;
    }

    public String getAuthor() {
	return author;
    }

    
    public double getPrice() {
	return price;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	
	sb.append("'");
	sb.append(name);
	sb.append("'");
	sb.append(", ");
	sb.append(author);
	sb.append(" | ");
	sb.append("price: ");
	sb.append(Double.toString(price));
	sb.append("$");
	return sb.toString();
    }

    public boolean isInStore() {
	return inStore;
    }

    public void setInStore(boolean inStore) {
	this.inStore = inStore;
    }

    
}
