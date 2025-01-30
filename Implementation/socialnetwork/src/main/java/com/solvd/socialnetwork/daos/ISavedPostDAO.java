package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.SavedPost;

public interface ISavedPostDAO extends IDAO <SavedPost>{
	SavedPost getById (Long id);
	SavedPost save (SavedPost entity);
	SavedPost update (SavedPost entity);
	void removeById(Long id);
}
