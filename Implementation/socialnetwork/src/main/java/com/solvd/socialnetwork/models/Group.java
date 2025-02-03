package com.solvd.socialnetwork.models;

import java.util.ArrayList;
import java.util.List;

import com.solvd.socialnetwork.enums.GroupType;

public class Group extends BaseEntity{
	private String groupName;
	private GroupType groupType;
	private Long groupCreatorId;
	
	private List<GroupMember> groupMembers = new ArrayList<GroupMember>();

	
	public Group(Long id, String groupName, GroupType groupType, Long groupCreatorId, List<GroupMember> groupMembers) {
		super(id);
		this.groupName = groupName;
		this.groupType = groupType;
		this.groupCreatorId = groupCreatorId;
		this.groupMembers = groupMembers;
	}

	public Group() {

	}

	public Group(Long id, String groupName, GroupType groupType, Long groupCreatorId) {
		super(id);
		this.groupName = groupName;
		this.groupType = groupType;
		this.groupCreatorId = groupCreatorId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public long getGroupCreatorId() {
		return groupCreatorId;
	}

	public void setGroupCreatorId(Long groupCreatorId) {
		this.groupCreatorId = groupCreatorId;
	}

	@Override
	public String toString() {
		return "Group [groupName=" + groupName + ", groupType=" + groupType + ", groupCreatorId=" + groupCreatorId
				+ ", groupMembers=" + groupMembers + "]";
	}
	
	

}
