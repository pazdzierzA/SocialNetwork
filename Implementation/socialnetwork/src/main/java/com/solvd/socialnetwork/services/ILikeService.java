package com.solvd.socialnetwork.services;

import com.solvd.socialnetwork.models.LikePost;

public interface ILikeService extends IService<LikePost>{
	LikePost getByPostId (Long id);
}