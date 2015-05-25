package com.danco.training.dobrilko.abstractjdbcdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.danco.training.dobrilko.genericdao.GenericDAO;
import com.danco.training.dobrilko.persistexception.PersistException;
import com.danco.training.dobrilko.primarykey.PKHolder;

public abstract class AbstractJDBCDao<T extends PKHolder<PK>, PK extends Integer>
		implements GenericDAO<T, PK> {

	private static final String ON_PERSIST_MODIFY_MORE_THEN_1_RECORD = "On persist modify more then 1 record: ";
	private static final String WHERE_ID_LAST_INSERT_ID = " WHERE id = last_insert_id();";
	private static final String EXCEPTION_ON_FIND_BY_PK_NEW_PERSIST_DATA = "Exception on findByPK new persist data.";

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws PersistException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws PersistException;

	public abstract String getCreateQuery();

	public abstract String getUpdateQuery();

	public abstract String getSelectQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)
			throws PersistException;

	@Override
	public void update(T object, Connection connection) throws PersistException {
		String sql = getUpdateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			prepareStatementForUpdate(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(ON_PERSIST_MODIFY_MORE_THEN_1_RECORD
						+ count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public void save(T object, Connection connection) throws PersistException {
		T persistInstance;

		String sql = getCreateQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			prepareStatementForInsert(statement, object);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new PersistException(ON_PERSIST_MODIFY_MORE_THEN_1_RECORD
						+ count);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}

	}

	@Override
	public List<T> getAll( Connection connection)
			throws PersistException {
		List<T> list;
		String sql = getSelectQuery();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			list = parseResultSet(rs);
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return list;
	}
}
