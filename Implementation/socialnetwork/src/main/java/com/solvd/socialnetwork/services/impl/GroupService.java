package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.services.IGroupService;

public class GroupService implements IGroupService {
	private IGroupDAO groupDAO;

	@Override
	public Group getById(Long id) {
		return groupDAO.getById(id);
	}

	@Override
	public Group save(Group entity) {
		return groupDAO.save(entity);
	}

	@Override
	public Group update(Group entity) {
		return groupDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		groupDAO.removeById(id);

	}

	@Override
	public List<Group> getByName(String name) {
		return groupDAO.getByName(name);
	}

}
