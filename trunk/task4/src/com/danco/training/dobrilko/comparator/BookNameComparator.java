package com.danco.training.dobrilko.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.model.BookType;

public class BookNameComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return a.getName().compareTo(b.getName());
    }

}
