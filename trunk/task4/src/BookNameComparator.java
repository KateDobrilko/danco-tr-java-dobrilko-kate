import java.util.Comparator;

public class BookNameComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return a.getName().compareTo(b.getName());
    }

}
