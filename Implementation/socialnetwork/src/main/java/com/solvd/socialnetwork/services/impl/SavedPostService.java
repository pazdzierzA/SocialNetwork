package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.models.SavedPost;
import com.solvd.socialnetwork.services.ISavedPostService;

public class SavedPostService extends BaseService implements ISavedPostService {
	private ISavedPostDAO savedPostDAO;

	public SavedPostService() {
		super();
		this.savedPostDAO = daoFactory.getSavedPostDAO();
	}

	@Override
	public SavedPost getById(Long id) {
		return savedPostDAO.getById(id);
	}

	@Override
	public Integer save(SavedPost entity) {
		return savedPostDAO.save(entity);
	}

	@Override
	public Integer update(SavedPost entity) {

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
