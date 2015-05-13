package com.danco.training.dobrilko.ModelDB.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Reply;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;

public class HibernateReplyDAO<update> extends
		AbstractHibernateDAO<Reply, Integer> {

	public HibernateReplyDAO() {
		super(Reply.class);
	}

	public void markReplyAsExecuted(Session session, Reply reply)
			throws PersistException {
		reply.setExecuted(true);
		saveOrUpdate(session, reply);

	}

	public void deleteReplyByBook(Session session, Book book) {
		for (Reply reply : getReplyByBook(session, book)) {
			session.delete(reply);
		}

	}

	public Book getBookByReplyId(Session session, Integer id)
			throws PersistException {

		Reply reply = getByPK(session, id);

		return reply.getBook();
	}

	@SuppressWarnings("unchecked")
	public List<Reply> getReplyByBook(Session session, Book book) {
		Criteria criteria = session.createCriteria(Reply.class);
		criteria.add(Restrictions.eq("book_id", book));
		return criteria.list();
	}
}
