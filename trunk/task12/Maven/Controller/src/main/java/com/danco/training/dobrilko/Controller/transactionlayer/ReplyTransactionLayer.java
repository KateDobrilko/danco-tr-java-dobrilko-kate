package com.danco.training.dobrilko.Controller.transactionlayer;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.danco.training.dobrilko.Controller.BookshopController;
import com.danco.training.dobrilko.Controller.parser.ParseUtil;
import com.danco.training.dobrilko.ModelDB.dao.HibernateReplyDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.entity.Order;
import com.danco.training.dobrilko.ModelDB.entity.Reply;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.training.DAOFactory;
import com.danco.training.dobrilko.ModelDB.training.HibernateDAOFactory;
import com.danco.training.dobrilko.hibernateutil.HibernateUtil;

public class ReplyTransactionLayer {
	private DAOFactory factory = new HibernateDAOFactory();

	private GenericDAO<Reply, Integer> daoReply;
	private Logger logger = Logger.getLogger(BookshopController.class);

	@SuppressWarnings("unchecked")
	public ReplyTransactionLayer() {
		try {
			daoReply = (GenericDAO<Reply, Integer>) factory.getDao(Order.class);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public String getReplies() throws PersistException {
		StringBuilder sb = new StringBuilder();
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		for (Reply reply : daoReply.getAll(session, "id")) {
			sb.append(ParseUtil.replyToString(reply));
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

	public String sortRepliesByNumber() throws PersistException {
		StringBuilder sb = new StringBuilder();
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		for (Reply reply : daoReply.getAll(session, "number")) {
			sb.append(ParseUtil.replyToString(reply));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public void addReply(String replyString) throws PersistException {

		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		Transaction tr = session.beginTransaction();
		daoReply.saveOrUpdate(session, ParseUtil.parseReplyString(replyString));
		tr.commit();
	}

	public void markRepliesAsExecuted() throws PersistException {

		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		Transaction tr = session.beginTransaction();
		for (Reply reply : daoReply.getAll(session, "id")) {
			@SuppressWarnings("rawtypes")
			Book book = (Book) ((HibernateReplyDAO) daoReply).getBookByReplyId(
					session, reply.getId());
			if (book.getNumberOfExemplars() != 0) {
				reply.setExecuted(true);
				daoReply.saveOrUpdate(session, reply);
			}
		}
		tr.commit();
	}

	public String getReplyById(Integer id) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		Reply reply = daoReply.getByPK(session, id);

		return ParseUtil.replyToString(reply);

	}

}
