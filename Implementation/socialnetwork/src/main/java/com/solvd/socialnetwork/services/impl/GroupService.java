package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.daos.mybatisImpl.GroupDAO;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.services.IGroupService;

public class GroupService implements IGroupService {
	private IGroupDAO groupDAO = new GroupDAO();

	@Override
	public Group getById(Long id) {
		return groupDAO.getById(id);
	}

	@Override
	public Integer save(Group entity) {
		return groupDAO.save(entity);
	}

	@Override
	public Integer update(Group entity) {
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
