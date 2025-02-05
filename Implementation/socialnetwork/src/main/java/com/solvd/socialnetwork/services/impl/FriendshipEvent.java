package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.FriendshipDAO;
import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;
import com.solvd.socialnetwork.services.IFriendshipEvent;

public class FriendshipEvent implements IFriendshipEvent {
	private IFriendshipDAO friendshipDAO = new FriendshipDAO();
	@Override
	public Friendship getById(Long id) {
		return friendshipDAO.getById(id);
	}

	@Override
	public Friendship save(Friendship entity) {
		return friendshipDAO.save(entity);
	}

	@Override
	public Friendship update(Friendship entity) {
		// TODO Auto-generated method stub
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
