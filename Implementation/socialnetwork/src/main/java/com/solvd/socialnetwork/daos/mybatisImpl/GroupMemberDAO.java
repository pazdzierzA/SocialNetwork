package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IGroupMemberDAO;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class GroupMemberDAO implements IGroupMemberDAO {

    @Override
    public GroupMember getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            return groupMemberDao.getById(id);
        }

    }

    @Override
    public GroupMember save(GroupMember entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            return groupMemberDao.save(entity);
        }
  
    }

    @Override
    public GroupMember update(GroupMember entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            return groupMemberDao.update(entity);
        }

    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            groupMemberDao.removeById(id);
        }

    }

    @Override
    public List<GroupMember> getByUserRole(UserRole userRole) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            return groupMemberDao.getByUserRole(userRole);
        }
    }

    @Override
    public List<User> getUsersByGroupId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupMemberDAO groupMemberDao = session.getMapper(IGroupMemberDAO.class);
            return groupMemberDao.getUsersByGroupId(id);
        }
    }

}
