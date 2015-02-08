import java.util.Comparator;

public class BookPublicationDateComparator implements Comparator<BookExemplar> {

    public int compare(BookExemplar a, BookExemplar b) {

	return a.getDateOfPublication().compareTo(b.getDateOfPublication());
    }

}

