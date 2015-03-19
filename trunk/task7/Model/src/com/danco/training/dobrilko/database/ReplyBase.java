package com.danco.training.dobrilko.database;

import java.io.Serializable;
import java.util.ArrayList;

import com.danco.training.dobrilko.bookshop.Bookshop;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Reply;

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
				r.setNumberOfRequests(r.getNumberOfRequests() + 1);
				idUnique = false;
				break;
			}
		}

		if (idUnique) {
			for (Book b : Bookshop.getInstance().getBookBase().getBooks()) {
				if ((b.getId() == reply.getBook().getId())
						&& (b.getDateOfAddition() == null)) {
					reply.setBook(b);
					boolean flag = false;
					
					for (Reply r : this.replies) {
						if (r.getBook().getId() == reply.getBook().getId()) {
							r.setNumberOfRequests(r.getNumberOfRequests() + 1);
							flag = true;
							break;
						}
					}

					if (flag == false) {
						this.replies.add(reply);
						break;
					}

				} else {
					idUnique = false;
				}

			}

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

	public Reply[] getRepliesArray() {
		Reply[] repliesArray = new Reply[replies.size()];
		repliesArray = replies.toArray(repliesArray);
		return repliesArray;
	}
}
