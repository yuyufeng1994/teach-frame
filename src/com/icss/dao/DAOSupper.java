package com.icss.dao;

import java.util.List;

/**
 * DAO的基本方法
 * @author jlzhou
 *
 * @param <T>
 */
public interface DAOSupper<T> {

	/**
	 * 添加
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 根据主键修改对象的其它属性
	 * @param t
	 */
	void modify(T t);
	
	/**
	 * 根据主键删除对象
	 * @param t
	 */
	void delete(T t);
	
	/**
	 * 根据对象主键获取完整的对象信息
	 * @param t
	 * @return
	 */
	T findById(T t);
	
	/**
	 * 获取所有对象的列表
	 * @return
	 */
	List<T> findAll();
}
