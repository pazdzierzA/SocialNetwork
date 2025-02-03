package com.solvd.socialnetwork.services;

public interface IService<T> {

	    T getById(Long id);
	    T save(T entity);
	    T update(T entity);
	    void removeById(Long id);
	}
