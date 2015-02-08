import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<BookType, ArrayList<BookExemplar>> books = new HashMap<BookType, ArrayList<BookExemplar>>();

    public Store() {
	
    }

    public Store(Map<BookType, ArrayList<BookExemplar>> books) {
	this.books = books;
    }

    public BookType[] sortByName(BookType[] array) {
         Arrays.sort(array,new BookNameComparator());
         return array;
    }
    
    public BookType[] sortByPublicationDate(BookExemplar[] array) {
        Arrays.sort(array,new BookPublicationDateComparator());
        return array;
   }
    
    public BookType[] sortByPrice(BookType[] array) {
        Arrays.sort(array,new BookPriceComparator());
        return array;
   }
    
    public BookType[] sortByInStore(BookType[] array) {
        Arrays.sort(array,new BookInStoreComparator());
        return array;
   }

    public BookType[] getBookTypes() {
	return (BookType[]) books.keySet().toArray();
    }

    public BookExemplar[] getBookExemplars() {
	return (BookExemplar[]) books.values().toArray();
    }

    public void setBooks(Map<BookType, ArrayList<BookExemplar>> books) {
	this.books = books;
    }

    public ArrayList<BookExemplar> getUnclaimedBooks() {
	ArrayList<BookExemplar> unclaimedBooks = new ArrayList<BookExemplar>();
	for (ArrayList<BookExemplar> bookList : books.values()) {
	    for (BookExemplar book : bookList) {
		if (book.isUnclaimed()) {
		    unclaimedBooks.add(book);
		}
	    }
	}
	return unclaimedBooks;
    }

    public void deleteBookType(BookType bookType) {

	books.remove(bookType);

    }

    public void addBookType(BookType book) {

	books.put(book, null);
    }

    public void deleteBookExemplar(BookExemplar bookExemplar) {

	for (BookType book : books.keySet()) {
	    if ((book.getName().equals(bookExemplar.getName()))
		    && (book.getAuthor().equals(bookExemplar.getAuthor()))
		    && (book.getPrice() == bookExemplar.getPrice())) {
		books.remove(book, bookExemplar);
		if(books.get(book).equals(null))
		{
		    BookType b = book;
		    books.remove(book);
		    b.setInStore(false);
		    books.put(b, null);
		    
		}
	    }
	}

    }

    public void addBookExemplar(BookExemplar bookExemplar) {

	BookType bt = new BookType();
	ArrayList<BookExemplar> be;
	for (BookType book : books.keySet()) {
	    if(book!= null){
	    if ((book.getName().equals(bookExemplar.getName()))
		    && (book.getAuthor().equals(bookExemplar.getAuthor()))
		    && (book.getPrice() == bookExemplar.getPrice())) {
		bt = book;
	    }
	    if(book.equals(null)){
	    this.addBookType(new BookType(bookExemplar.getName(),bookExemplar.getAuthor(), bookExemplar.getPrice()));
	    }}
	}
	be = books.get(bt);
	books.remove(bt);
	bt.setInStore(true);
	bookExemplar.setDateOfAddition(Calendar.getInstance());
	if(be!=null){}
	else{be = new ArrayList<BookExemplar>();
	be.add(bookExemplar);
	}
	books.put(bt, be);
    }

    public String showBooks(BookType[] bookTypes) {
	StringBuilder sb = new StringBuilder();
	for(BookType bookType: bookTypes){
	    sb.append(bookType.toString() + System.lineSeparator());
	}
	
	return sb.toString();
    }

    public String showUnclaimedBooks(BookExemplar[] bookExemplars) {
	StringBuilder sb = new StringBuilder();
	for(BookExemplar bookExemplar: bookExemplars){
	    sb.append(bookExemplar.toString() + System.lineSeparator());
	}
	return sb.toString();
    }

}
