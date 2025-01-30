package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Comment;

public interface ICommentDAO extends IDAO<Comment>{
	Comment getById(Long id);
	Comment save (Comment entity);
	Comment update (Comment entity);
	void removeById(Long id);

}
