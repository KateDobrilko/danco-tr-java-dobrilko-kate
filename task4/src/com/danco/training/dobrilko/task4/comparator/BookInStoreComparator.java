package com.danco.training.dobrilko.task4.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.task4.model.BookType;

public class BookInStoreComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return ((Boolean)a.isInStore()).compareTo(((Boolean)b.isInStore()));
    }

}