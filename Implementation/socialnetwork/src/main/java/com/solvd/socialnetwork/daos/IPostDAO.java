package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Post;

public interface IPostDAO extends IDAO<Post> {
	Post getById (Long id);
	Post save (Post entity);
	Post update (Post entity);
	void removeById(Long id);
}
