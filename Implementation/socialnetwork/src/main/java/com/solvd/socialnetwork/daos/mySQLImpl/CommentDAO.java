package com.solvd.socialnetwork.daos.mySQLImpl;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;

public class CommentDAO implements ICommentDAO {
	public final static String GET_BY_ID = "SELECT * FROM Comments WHERE id = ?";
	public final static String GET_BY_AUTHOR_ID = "SELECT * FROM Comments WHERE author_id =?";
	public final static String GET_BY_POST_ID = "SELECT * FROM Comments WHERE post_id =?";

	@Override
	public Comment getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment save(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment update(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment getByAuthorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getByPostId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
