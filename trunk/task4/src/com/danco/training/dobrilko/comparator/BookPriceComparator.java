package com.danco.training.dobrilko.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.model.BookType;

public class BookPriceComparator implements Comparator<BookType> {

    public int compare(BookType a, BookType b) {

	return ((Double)a.getPrice()).compareTo(((Double)b.getPrice()));
    }

}

