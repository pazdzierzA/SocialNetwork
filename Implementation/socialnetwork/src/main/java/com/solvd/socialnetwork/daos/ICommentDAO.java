package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Comment;

public interface ICommentDAO extends IDAO<Comment>{
	Comment getByAuthorId(Long id);
	Comment getByPostId(Long id);

}
