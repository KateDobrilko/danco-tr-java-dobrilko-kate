package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entity.Reply;

public class ReplyNumberComparator implements Comparator<Reply> {
	public int compare(Reply a, Reply b) {

		return (Boolean.compare(a.isExecuted(), b.isExecuted()));
	}
}