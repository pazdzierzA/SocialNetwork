package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.SavedPost;

public interface ISavedPostService extends IService<SavedPost>{
	List<SavedPost> getByUserId (Long id);
}