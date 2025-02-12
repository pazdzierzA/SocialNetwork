package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Post;

public interface IPostDAO extends IDAO<Post> {
	void incrementLikeQuantity(Long id);

}
