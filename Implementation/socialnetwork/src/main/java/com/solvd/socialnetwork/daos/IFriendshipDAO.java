package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;

public interface IFriendshipDAO extends IDAO<Friendship>{
	public List<Friendship> getByType(FriendshipType type);
	List<Friendship> getByStatus(FriendshipStatus status);

}
