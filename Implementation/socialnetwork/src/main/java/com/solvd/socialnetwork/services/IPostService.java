package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.models.Post;

public interface IPostService extends IService<Post>{
	void incrementLikeQuantity(Long id);
}