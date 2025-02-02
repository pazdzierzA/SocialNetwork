package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.SavedPost;

public interface ISavedPostDAO extends IDAO <SavedPost>{
	List<SavedPost> getByUserId (Long id);
}
