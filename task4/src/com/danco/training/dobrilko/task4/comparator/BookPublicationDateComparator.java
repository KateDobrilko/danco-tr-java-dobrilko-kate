package com.danco.training.dobrilko.task4.comparator;
import java.util.Comparator;

import com.danco.training.dobrilko.task4.model.BookExemplar;

public class BookPublicationDateComparator implements Comparator<BookExemplar> {

    public int compare(BookExemplar a, BookExemplar b) {

	return a.getDateOfPublication().compareTo(b.getDateOfPublication());
    }

}

