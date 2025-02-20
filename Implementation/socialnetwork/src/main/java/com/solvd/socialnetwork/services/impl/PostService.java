package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.services.IPostService;

public class PostService extends BaseService implements IPostService {
	private IPostDAO postDAO;
	
	public PostService() {
		super();
		this.postDAO = daoFactory.getPostDAO();
	}
	@Override
	public Post getById(Long id) {
		return postDAO.getById(id);
	}

	@Override
	public Integer save(Post entity) {
		return postDAO.save(entity);
	}

	@Override
	public Integer update(Post entity) {
		return postDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		postDAO.removeById(id);

	}

	@Override
	public void incrementLikeQuantity(Long id) {
		postDAO.incrementLikeQuantity(id);

	}

}
