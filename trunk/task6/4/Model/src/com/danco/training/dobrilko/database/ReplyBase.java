package com.danco.training.dobrilko.database;

import java.io.Serializable;
import java.util.ArrayList;

import com.danco.training.dobrilko.entitiy.Reply;

public class ReplyBase implements Serializable {

	private static final long serialVersionUID = 9040205567499903654L;
	private ArrayList<Reply> replies;

	public ReplyBase() {
		this.setReplies(new ArrayList<Reply>());
	}

	public ReplyBase(ArrayList<Reply> replies) {
		this.setReplies(replies);
	}

	public ArrayList<Reply> getReplies() {
		return replies;
	}

	public void setReplies(ArrayList<Reply> replies) {
		this.replies = replies;
	}

	public boolean add(Reply reply) {

		boolean idUnique = true;
		for (Reply r : this.replies) {
			if (r.getId() == reply.getId()) {
				update(r.getId(), reply);
				idUnique = false;
				break;
			}
		}
		if (idUnique) {

			this.replies.add(reply);
		}

		return idUnique;
	}

	public boolean delete(int id) {
		boolean idUnique = true;
		for (Reply r : this.replies) {
			if (r.getId() == id) {
				idUnique = false;
				break;
			}
		}
		if (idUnique) {
			this.replies.removeIf((Reply reply) -> reply.getId() == id);
		}

		return idUnique;

	}

	public boolean update(int id, Reply reply) {
		boolean idMatches = false;
		for (Reply r : this.replies) {
			if (r.getId() == id) {
				idMatches = true;
				r = reply;
				break;
			}
		}
		return idMatches;
	}

	public Reply getById(int id) {
		Reply reply = new Reply();
		for (Reply r : this.replies) {
			if (r.getId() == id) {
				reply = r;
				break;
			}
		}
		return reply;
	}
}
