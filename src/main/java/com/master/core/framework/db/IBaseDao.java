package com.master.core.framework.db;

import java.util.List;
import com.master.core.framework.exception.DataAccessException;

public interface IBaseDao<T> {
	/**
	 * 根据条件查找信息列表
	 * @param t 查询信息对象
	 * @return 对象列表
	 * @throws PersistenceException
	 */
	public List<T> find(T t) throws DataAccessException;
	
	/**
	 * 获取所有信息
	 * @return
	 * @throws DataAccessException
	 */
	public List<T> findAll() throws DataAccessException;
	/**
	 * 根据条件查找信息列表
	 * @param t 查询信息对象
	 * @return 对象列表
	 * @throws PersistenceException
	 */
	public List<T> findByPage(QueryBean<T> query) throws DataAccessException;
	/**
	 * 增加一个信息对象
	 * @param t 信息对象
	 * @return 受影响行数
	 * @throws PersistenceException
	 */
	public int insert(T t) throws DataAccessException;
	/**
	 * 修改信息对象
	 * @param t 信息对象
	 * @return 受影响行数
	 * @throws PersistenceException
	 */
	public int update(T t) throws DataAccessException;
	/**
	 * 删除信息对象
	 * @param t 信息对象
	 * @return 受影响行数
	 * @throws PersistenceException
	 */
	public int delete(T t) throws DataAccessException;
	
	/**
	 * 获取数量
	 * @param t
	 * @return
	 * @throws DataAccessException
	 */
	public int count(T t) throws DataAccessException;
}
