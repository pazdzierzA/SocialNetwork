package com.solvd.socialnetwork.models;

import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;

public class Friendship extends BaseEntity {

	private FriendshipStatus status;
	private FriendshipType type;
	private Long user_id;
	private Long friend_id;

	public Friendship(Long id, FriendshipStatus status, FriendshipType type, Long user_id, Long friend_id) {
		super(id);
		this.status = status;
		this.type = type;
		this.user_id = user_id;
		this.friend_id = friend_id;
	}

	public FriendshipStatus getStatus() {
		return status;
	}

	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}

	public FriendshipType getType() {
		return type;
	}

	public void setType(FriendshipType type) {
		this.type = type;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}

}
