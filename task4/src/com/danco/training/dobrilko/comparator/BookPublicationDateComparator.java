package com.danco.training.dobrilko.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.model.BookExemplar;

public class BookPublicationDateComparator implements Comparator<BookExemplar> {

    public int compare(BookExemplar a, BookExemplar b) {

	return a.getDateOfPublication().compareTo(b.getDateOfPublication());
    }

}

