package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.daos.mybatisImpl.FriendshipDAO;
import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;
import com.solvd.socialnetwork.services.IFriendshipService;

public class FriendshipService implements IFriendshipService {
	private IFriendshipDAO friendshipDAO = new FriendshipDAO();
	@Override
	public Friendship getById(Long id) {
		return friendshipDAO.getById(id);
	}

	@Override
	public Integer save(Friendship entity) {
		return friendshipDAO.save(entity);
	}

	@Override
	public Integer update(Friendship entity) {
		return friendshipDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		friendshipDAO.removeById(id);

	}

	@Override
	public List<Friendship> getByType(FriendshipType type) {
		return friendshipDAO.getByType(type);
	}

	@Override
	public List<Friendship> getByStatus(FriendshipStatus status) {
		return friendshipDAO.getByStatus(status);
	}

}
