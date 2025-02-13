package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class GroupDAO implements IGroupDAO{

    @Override
    public Group getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupDAO groupDao = session.getMapper(IGroupDAO.class);
            return groupDao.getById(id);
        }
    }

    @Override
    public Group save(Group entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupDAO groupDao = session.getMapper(IGroupDAO.class);
            return groupDao.save(entity);
        }
       
    }

    @Override
    public Group update(Group entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupDAO groupDao = session.getMapper(IGroupDAO.class);
            return groupDao.update(entity);
        }
     
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupDAO groupDao = session.getMapper(IGroupDAO.class);
            groupDao.removeById(id);
        }
    }

    @Override
    public List<Group> getByName(String name) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IGroupDAO groupDao = session.getMapper(IGroupDAO.class);
            return groupDao.getByName(name);
        }
    }


}
