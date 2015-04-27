package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Book;
import com.danco.training.dobrilko.entity.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class MySqlReplyDao extends AbstractJDBCDao<Reply, Integer> {

	private Logger logger = Logger.getLogger(MySqlBookDao.class);

	@Override
	public String getSelectQuery(String parameterName) {
		StringBuilder sb = new StringBuilder();
		try {
			Reply.class.getDeclaredField(parameterName);
			sb.append("SELECT bookId, numberOfRequests,  id,  executed FROM Bookshop.Reply ORDER BY ");
			sb.append(parameterName);
			sb.append(";");

		} catch (NoSuchFieldException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		}

		return sb.toString();

	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO (bookId, numberOfRequests,  id,  executed)  \n"
				+ "VALUES (?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Bookshop.Reply SET bookId= ? numberOfRequests = ? eecuted = ? WHERE id= ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Bookshop.Reply WHERE id= ?;";
	}

	@Override
	public Reply create(Connection connection) throws PersistException {
		Reply g = new Reply();
		return persist(g, connection);
	}

	public MySqlReplyDao() {
		super();
	}

	@Override
	protected List<Reply> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Reply> result = new LinkedList<Reply>();
		try {
			while (rs.next()) {
				Reply reply = new Reply();
				reply.setId(rs.getInt("id"));
				reply.setNumberOfRequests(rs.getInt("numberOfRequests"));
				reply.setExecuted(rs.getBoolean("executed"));
				Book book = new Book();
				book.setId(rs.getInt("bookId"));
				reply.setBook(book);
				result.add(reply);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Reply object) throws PersistException {
		try {
			statement.setInt(1, object.getBook().getId());
			statement.setInt(2, object.getNumberOfRequests());
			statement.setInt(3, object.getId());
			statement.setBoolean(4, object.isExecuted());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Reply object) throws PersistException {
		try {
			statement.setInt(1, object.getBook().getId());
			statement.setInt(2, object.getNumberOfRequests());
			statement.setInt(3, object.getId());
			statement.setBoolean(4, object.isExecuted());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

}
