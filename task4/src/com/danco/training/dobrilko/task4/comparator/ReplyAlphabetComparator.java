package com.danco.training.dobrilko.task4.comparator;

import java.util.Comparator;



import com.danco.training.dobrilko.task4.model.Reply;

public class ReplyAlphabetComparator implements Comparator<Reply> {
    public int compare(Reply a, Reply b) {
	
		return a.getBookType().getName().compareTo(b.getBookType().getName());
    }
}
