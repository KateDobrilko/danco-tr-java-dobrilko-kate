package com.danco.training.dobrilko.mysql;

import com.danco.training.dobrilko.dao.AbstractJDBCDao;
import com.danco.training.dobrilko.dao.PersistException;
import com.danco.training.dobrilko.entity.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlReplyDao extends AbstractJDBCDao<Reply, Integer> {

	private class PersistReply extends Reply {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4641921138250005105L;

		public void setId(int id) {
			super.setId(id);
		}
	}

	@Override
	public String getSelectQuery() {
		return "SELECT bookId, numberOfRequests,  id,  executed FROM Bookshop.Reply";
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
	public Reply create() throws PersistException {
		Reply g = new Reply();
		return persist(g);
	}

	public MySqlReplyDao(Connection connection) {
		super(connection);
	}

	@Override
	protected List<Reply> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Reply> result = new LinkedList<Reply>();
		try {
			while (rs.next()) {
				PersistReply Reply = new PersistReply();
				Reply.setId(rs.getInt("id"));
				Reply.setNumberOfRequests(rs.getInt("numberOfRequests"));
				Reply.setExecuted(rs.getBoolean("executed"));
				Reply.setBookId(rs.getInt("bookId"));
				result.add(Reply);
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
			statement.setInt(1, object.getBookId());
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
			statement.setInt(1, object.getBookId());
			statement.setInt(2, object.getNumberOfRequests());
			statement.setInt(3, object.getId());
			statement.setBoolean(4, object.isExecuted());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

}
