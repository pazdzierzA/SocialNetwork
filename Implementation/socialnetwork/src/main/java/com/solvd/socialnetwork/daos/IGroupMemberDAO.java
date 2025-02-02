package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;

public interface IGroupMemberDAO extends IDAO<GroupMember>{
	List<GroupMember> getByUserRole(UserRole userRole);
	List<User> getUsersByGroupId(Long id) ;
}
