package com.danco.training.dobrilko.task4.model;
import java.util.ArrayList;

import com.danco.training.dobrilko.task4.comparator.ReplyAlphabetComparator;
import com.danco.training.dobrilko.task4.comparator.ReplyNumberComparator;

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
	this.replies.add(reply);
    }

    public void executeReply(Store store, Reply reply) {
	store.addBookType(reply.getBookType());
	reply.setExecuted(true);
	
    }

    public void sortByAlphabet(){
	replies.sort(new ReplyAlphabetComparator());
    }
    
    public void sortByNumber(){
	replies.sort(new ReplyNumberComparator());
    }
    
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("ReplySystem "+System.lineSeparator()+replies.size()+System.lineSeparator());
	for (Reply reply : replies) {
	    sb.append(reply.toString() + System.lineSeparator());
	}
	return sb.toString();
    }

}
