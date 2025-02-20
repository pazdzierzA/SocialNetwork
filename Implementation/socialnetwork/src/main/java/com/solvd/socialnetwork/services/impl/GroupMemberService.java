package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IGroupMemberDAO;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.IGroupMemberService;

public class GroupMemberService extends BaseService implements IGroupMemberService {
	private IGroupMemberDAO groupMemberDAO;

	public GroupMemberService() {
		super();
		this.groupMemberDAO = daoFactory.getGroupMemberDAO();
	}
	@Override
	public GroupMember getById(Long id) {

		return groupMemberDAO.getById(id);
	}

	@Override
	public Integer save(GroupMember entity) {
		return groupMemberDAO.save(entity);
	}

	@Override
	public Integer update(GroupMember entity) {
		return groupMemberDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		groupMemberDAO.removeById(id);
		
	}

	@Override
	public List<GroupMember> getByUserRole(UserRole userRole) {
		return groupMemberDAO.getByUserRole(userRole);
	}

	@Override
	public List<User> getUsersByGroupId(Long id) {
		return groupMemberDAO.getUsersByGroupId(id);
	}

}
