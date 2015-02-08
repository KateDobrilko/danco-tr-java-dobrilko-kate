import java.util.Comparator;

public class BookInStoreComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return ((Boolean)a.isInStore()).compareTo(((Boolean)b.isInStore()));
    }

}