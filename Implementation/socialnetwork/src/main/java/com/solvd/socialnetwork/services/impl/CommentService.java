package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.services.ICommentService;

public class CommentService extends BaseService implements ICommentService {
	private ICommentDAO commentDAO;

	public CommentService() {
		super();
		this.commentDAO = daoFactory.getCommentDAO();
	}
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
	public Integer save(Comment entity) {

		return commentDAO.save(entity);
	}

	@Override
	public Integer update(Comment entity) {
		return commentDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		commentDAO.removeById(id);
		
	}

	

}
