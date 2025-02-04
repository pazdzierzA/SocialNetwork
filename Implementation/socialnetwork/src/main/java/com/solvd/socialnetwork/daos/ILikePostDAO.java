package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.LikePost;

public interface ILikePostDAO extends IDAO <LikePost>{
	LikePost getByPostId (Long id);
}
