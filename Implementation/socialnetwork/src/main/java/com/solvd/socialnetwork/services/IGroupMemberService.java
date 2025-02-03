package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.impl.GroupMemberService;

public interface IGroupMemberService extends IService<GroupMember>{
	List<GroupMember> getByUserRole(UserRole userRole);
	List<User> getUsersByGroupId(Long id) ;
}