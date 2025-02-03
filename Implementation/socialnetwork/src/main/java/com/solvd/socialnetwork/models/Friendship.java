package com.solvd.socialnetwork.models;

import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;

public class Friendship extends BaseEntity {

	private FriendshipStatus status;
	private FriendshipType type;
	private Long userId;
	private Long friendId;

	public Friendship(Long id, FriendshipStatus status, FriendshipType type, Long user_id, Long friend_id) {
		super(id);
		this.status = status;
		this.type = type;
		this.userId = user_id;
		this.friendId = friend_id;
	}

	public Friendship() {
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	@Override
	public String toString() {
		return "Friendship [status=" + status + ", type=" + type + ", userId=" + userId + ", friendId=" + friendId
				+ "]";
	}
	
	

}
