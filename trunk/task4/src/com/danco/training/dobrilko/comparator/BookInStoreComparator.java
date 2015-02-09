package com.danco.training.dobrilko.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.model.BookType;

public class BookInStoreComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return ((Boolean)a.isInStore()).compareTo(((Boolean)b.isInStore()));
    }

}