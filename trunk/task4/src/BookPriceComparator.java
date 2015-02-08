import java.util.Comparator;

public class BookPriceComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return ((Double)a.getPrice()).compareTo(((Double)b.getPrice()));
    }

}

