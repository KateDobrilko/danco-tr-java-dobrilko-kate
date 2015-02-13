package com.danco.training.dobrilko.task4.service;

import java.util.ArrayList;

import com.danco.training.dobrilko.task4.model.Reply;
import com.danco.training.dobrilko.task4.comparator.ReplyAlphabetComparator;
import com.danco.training.dobrilko.task4.comparator.ReplyNumberComparator;
import com.danco.training.dobrilko.task4.service.Store;

public class ReplySystem {
    private ArrayList<Reply> replies = new ArrayList<Reply>();

    public ReplySystem() {

    }

    public ReplySystem(ArrayList<Reply> replies) {
	this.replies = replies;
    }

    public ArrayList<Reply> getReplies() {
	return replies;
    }

    public void addReply(Reply reply) {
	if (!this.replies.contains(reply)) {
	    this.replies.add(reply);
	    return;
	} else {
	    for (Reply r : this.replies) {
		boolean condition =r.getBook().getId()==reply.getBook().getId();
		if (condition) {
		    r.setNumberOfRequests(r.getNumberOfRequests() + 1);
		    break;

		} 
	    }
	}

    }

    public void executeReply(Store store, Reply reply) {
	store.addBook(reply.getBook(), this);
	reply.setExecuted(true);

    }

    public void sortByAlphabet() {
	replies.sort(new ReplyAlphabetComparator());
    }

    public void sortByNumber() {
	replies.sort(new ReplyNumberComparator());
    }

    public String toString() {

	StringBuilder sb = new StringBuilder();
	sb.append(Integer.toString(replies.size()));
	sb.append(System.lineSeparator());
	replies.forEach((Reply reply) -> sb.append(reply.toString()
		+ System.lineSeparator()));
	return sb.toString();
    }
}
