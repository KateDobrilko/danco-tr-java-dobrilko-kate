package com.danco.training.dobrilko.bookshop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.bookshop.comparator.ReplyAlphabetComparator;
import com.danco.training.dobrilko.bookshop.comparator.ReplyNumberComparator;
import com.danco.training.dobrilko.bookshop.model.Reply;

public class ReplySystem {
    public Logger logger = Logger.getLogger(ReplySystem.class);
    private ArrayList<Reply> replies = new ArrayList<Reply>();

    public ReplySystem() {

    }

    public ReplySystem(ArrayList<Reply> replies) {
	this.replies = replies;
    }

    public ArrayList<Reply> getReplies() {
	return replies;
    }

    public void getReplies(ArrayList<Reply> replies) {
	this.replies = replies;
    }

    public void addReply(Reply reply) {
	if (!this.replies.contains(reply)) {
	    this.replies.add(reply);
	    return;
	} else {
	    for (Reply r : this.replies) {
		boolean condition = r.getBook().getId() == reply.getBook()
			.getId();
		if (condition) {
		    r.setNumberOfRequests(r.getNumberOfRequests() + 1);
		    break;

		}
	    }
	}

    }

    public void executeReply(Store store, int id) {
	if (store != null) {
	    Reply r = null;
	    for (Reply reply : replies) {
		if (reply.getId() == id) {
		    r = reply;
		}
	    
	    r.getBook().setDateOfAddition(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
	    store.addBook(r.getBook(), this);
	    r.setExecuted(true);}
	    if(r==null){
		logger.warn("Reply with id:"+Integer.toString(id)+" doesn't exists.");
	    }
	    }
	    
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

    public void setReplies(ArrayList<Reply> replies) {
	this.replies = replies;
    }
}
