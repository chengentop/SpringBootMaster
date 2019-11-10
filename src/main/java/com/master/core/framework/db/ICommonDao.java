package com.master.core.framework.db;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ICommonDao {
	public Timestamp currentTimestamp() throws DataAccessException;
	
	public <T> T selectOne(String statement) throws DataAccessException;

	public <T> T selectOne(String statement, Object parameter) throws DataAccessException;

	public <E> List<E> selectList(String statement) throws DataAccessException;

	public <E> List<E> selectList(String statement, Object parameter) throws DataAccessException;
	
	public int update(String statement, Object parameter) throws DataAccessException;
}
