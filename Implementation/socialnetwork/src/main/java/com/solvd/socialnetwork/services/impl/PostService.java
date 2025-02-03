package com.solvd.socialnetwork.services.impl;

import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.services.IPostService;

public class PostService implements IPostService {
	private IPostDAO postDAO;

	@Override
	public Post getById(Long id) {
		return postDAO.getById(id);
	}

	@Override
	public Post save(Post entity) {
		return postDAO.save(entity);
	}

	@Override
	public Post update(Post entity) {
		return postDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		postDAO.removeById(id);

	}

	@Override
	public void incrementLikeCount(Long id) {
		postDAO.incrementLikeCount(id);

	}

}
