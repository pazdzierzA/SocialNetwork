package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.daos.mybatisImpl.CommentDAO;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.services.ICommentService;

public class CommentService implements ICommentService {
	private ICommentDAO commentDAO = new CommentDAO();
	@Override
	public List<Comment> getCommentByAuthorId(Long id) {
		return commentDAO.getByAuthorId(id);
		
	}

	@Override
	public List<Comment> getCommentByPostId(Long id) {

		return commentDAO.getByPostId(id);
	}

	@Override
	public Comment getById(Long id) {
		return commentDAO.getById(id);
	}

	@Override
	public Comment save(Comment entity) {

		return commentDAO.save(entity);
	}

	@Override
	public Comment update(Comment entity) {
		return commentDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		commentDAO.removeById(id);
		
	}

	

}
