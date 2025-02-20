package com.solvd.socialnetwork.services;


public interface IService<T> {
	    T getById(Long id);
	    Integer save(T entity);
	    Integer update(T entity);
	    void removeById(Long id);
	}
