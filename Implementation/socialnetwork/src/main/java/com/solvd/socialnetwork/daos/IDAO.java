package com.solvd.socialnetwork.daos;


public interface IDAO<T> {
	T getById (Long id);
	Integer save (T entity);
	Integer update (T entity);
	void removeById(Long id);
}
