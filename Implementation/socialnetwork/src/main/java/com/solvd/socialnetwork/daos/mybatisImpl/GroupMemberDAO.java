package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import com.solvd.socialnetwork.daos.IGroupMemberDAO;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;

public class GroupMemberDAO extends AbstractMyBatisDAO implements IGroupMemberDAO {
    private IGroupMemberDAO groupMemberDAO;

    @Override
    public GroupMember getById(Long id) {
        return execute(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            return groupMemberDAO.getById(id);
        });

    }

    @Override
    public Integer save(GroupMember entity) {
        return execute(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            return groupMemberDAO.save(entity);
        });

    }

    @Override
    public Integer update(GroupMember entity) {
        return execute(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            return groupMemberDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            groupMemberDAO.removeById(id);
        });

    }

    @Override
    public List<GroupMember> getByUserRole(UserRole userRole) {
        return execute(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            return groupMemberDAO.getByUserRole(userRole);
        });
    }

    @Override
    public List<User> getUsersByGroupId(Long id) {
        return execute(session -> {
            groupMemberDAO = getMapper(IGroupMemberDAO.class, session);
            return groupMemberDAO.getUsersByGroupId(id);
        });
    }

}
