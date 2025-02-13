package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.Comment;

public interface ICommentDAO extends IDAO<Comment>{
	 List<Comment> getByAuthorId(Long id);
	 List<Comment> getByPostId(Long id);

}
