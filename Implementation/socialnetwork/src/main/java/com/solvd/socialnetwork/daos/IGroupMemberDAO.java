package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.GroupMember;

public interface IGroupMemberDAO extends IDAO<GroupMember>{
	GroupMember getById (Long id);
	GroupMember save (GroupMember entity);
	GroupMember update (GroupMember entity);
	void removeById(Long id);
}
