package com.solvd.socialnetwork.models;

import com.solvd.socialnetwork.enums.UserRole;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)

public class GroupMember extends BaseEntity {
	private UserRole userRole;
	private Long groupId;
	private Long userId;

	public GroupMember(Long id, UserRole userRole, Long groupId, Long userId) {
		super(id);
		this.userRole = userRole;
		this.groupId = groupId;
		this.userId = userId;
	}

	public GroupMember() {
	}

	public GroupMember(UserRole userRole) {
		this.userRole = userRole;
	}
	public GroupMember(UserRole userRole, Long groupId, Long userId) {
		this.userRole = userRole;
		this.groupId = groupId;
		this.userId = userId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "GroupMember [userRole=" + userRole + ", groupId=" + groupId + ", userId=" + userId + "]";
	}

	
	

}
