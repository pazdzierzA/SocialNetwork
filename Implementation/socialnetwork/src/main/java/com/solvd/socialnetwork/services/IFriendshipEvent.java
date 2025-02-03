package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;

public interface IFriendshipEvent extends IService<Friendship>{
	List<Friendship> getByType(FriendshipType type);
	List<Friendship> getByStatus(FriendshipStatus status);
}