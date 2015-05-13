package com.danco.training.dobrilko.Controller.transactionlayer;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.danco.training.dobrilko.Controller.BookshopController;
import com.danco.training.dobrilko.Controller.parser.ParseUtil;
import com.danco.training.dobrilko.ModelDB.dao.HibernateBookDAO;
import com.danco.training.dobrilko.ModelDB.entity.Book;
import com.danco.training.dobrilko.ModelDB.genericdao.GenericDAO;
import com.danco.training.dobrilko.ModelDB.persistexception.PersistException;
import com.danco.training.dobrilko.ModelDB.training.DAOFactory;
import com.danco.training.dobrilko.ModelDB.training.HibernateDAOFactory;
import com.danco.training.dobrilko.hibernateutil.HibernateUtil;

public class BookTransactionLayer {
	private DAOFactory factory = new HibernateDAOFactory();
	private GenericDAO<Book, Integer> daoBook;
	private Logger logger = Logger.getLogger(BookshopController.class);

	@SuppressWarnings("unchecked")
	public BookTransactionLayer() {
		try {
			daoBook = (GenericDAO<Book, Integer>) factory.getDao(Book.class);
		} catch (PersistException e) {
			logger.error(e);
		}

	}

	public void addBook(String bookString) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		session.beginTransaction();
		daoBook.saveOrUpdate(session, ParseUtil.parseBookString(bookString));
		session.getTransaction().commit();
	}

	public void deleteBook(Integer id) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		daoBook.delete(session, daoBook.getByPK(session, id));

	}

	public String getBooks() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		StringBuilder sb = new StringBuilder();
		Transaction tr = session.beginTransaction();
		for (Book book : daoBook.getAll(session, "id")) {
			sb.append(ParseUtil.bookToString(book));
			sb.append(System.lineSeparator());
		}
		tr.commit();
		return sb.toString();
	}

	public String getUnclaimedBooks() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();
		StringBuilder sb = new StringBuilder();
		Transaction tr = session.beginTransaction();
		for (Book book : ((HibernateBookDAO) daoBook).getUnclaimed(session)) {
			sb.append(ParseUtil.bookToString(book));
			sb.append(System.lineSeparator());
		}
		tr.commit();
		return sb.toString();
	}

	public String sortBookByPublicationDate() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Book book : daoBook.getAll(session, "publicationDate")) {
			sb.append(ParseUtil.bookToString(book));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public String sortBookByName() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Book book : daoBook.getAll(session, "name")) {
			sb.append(ParseUtil.bookToString(book));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public String sortBookByPrice() throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		for (Book book : daoBook.getAll(session, "price")) {
			sb.append(ParseUtil.bookToString(book));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public String getBookById(Integer id) throws PersistException {
		Session session = HibernateUtil.getInstance().getSessionFactory()
				.getCurrentSession();

		StringBuilder sb = new StringBuilder();
		sb.append(ParseUtil.bookToString(daoBook.getByPK(session, id)));

		return sb.toString();
	}

}
