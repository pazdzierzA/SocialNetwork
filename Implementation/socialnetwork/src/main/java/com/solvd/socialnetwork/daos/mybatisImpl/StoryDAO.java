package com.solvd.socialnetwork.daos.mybatisImpl;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class StoryDAO implements IStoryDAO{

    @Override
    public Story getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IStoryDAO storyDao = session.getMapper(IStoryDAO.class);
            return storyDao.getById(id);
        }
    }

    @Override
    public Story save(Story entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IStoryDAO storyDao = session.getMapper(IStoryDAO.class);
            return storyDao.save(entity);
        }
    }

    @Override
    public Story update(Story entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IStoryDAO storyDao = session.getMapper(IStoryDAO.class);
            return storyDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IStoryDAO storyDao = session.getMapper(IStoryDAO.class);
            storyDao.removeById(id);
        }
    }

    @Override
    public void removeByStoryCreatorId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IStoryDAO storyDao = session.getMapper(IStoryDAO.class);
            storyDao.removeByStoryCreatorId(id);
        }
    }
}
