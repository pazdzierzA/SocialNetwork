package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.SavedPostDAO;
import com.solvd.socialnetwork.models.SavedPost;
import com.solvd.socialnetwork.services.ISavedPostService;

public class SavedPostService implements ISavedPostService {
	private ISavedPostDAO savedPostDAO = new SavedPostDAO();

	@Override
	public SavedPost getById(Long id) {
		return savedPostDAO.getById(id);
	}

	@Override
	public SavedPost save(SavedPost entity) {
		return savedPostDAO.save(entity);
	}

	@Override
	public SavedPost update(SavedPost entity) {

		return savedPostDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		savedPostDAO.removeById(id);

	}

	@Override
	public List<SavedPost> getByUserId(Long id) {
		return savedPostDAO.getByUserId(id);
	}

}
