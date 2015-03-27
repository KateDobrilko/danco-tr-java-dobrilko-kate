package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entity.Reply;

public class ReplyAlphabetComparator implements Comparator<Reply> {
	public int compare(Reply a, Reply b) {

		return a.getBook().getName().compareTo(b.getBook().getName());
	}
}
