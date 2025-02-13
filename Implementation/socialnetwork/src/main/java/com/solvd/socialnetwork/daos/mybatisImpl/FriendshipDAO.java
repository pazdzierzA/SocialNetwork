package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class FriendshipDAO implements IFriendshipDAO{

    @Override
    public Friendship getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            return friendshipDao.getById(id);
        }
    }

    @Override
    public Friendship save(Friendship entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            return friendshipDao.save(entity);
        }
    }

    @Override
    public Friendship update(Friendship entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            return friendshipDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            friendshipDao.removeById(id);
        }
    }

    @Override
    public List<Friendship> getByType(FriendshipType type) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            return friendshipDao.getByType(type);
        }

    }

    @Override
    public List<Friendship> getByStatus(FriendshipStatus status) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IFriendshipDAO friendshipDao = session.getMapper(IFriendshipDAO.class);
            return friendshipDao.getByStatus(status);
        }
    }
    

}
