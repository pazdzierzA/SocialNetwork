package com.solvd.socialnetwork.daos.mybatisImpl;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class PostDAO implements IPostDAO{

    @Override
    public Post getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IPostDAO postDao = session.getMapper(IPostDAO.class);
            return postDao.getById(id);
        }
    }

    @Override
    public Integer save(Post entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IPostDAO postDao = session.getMapper(IPostDAO.class);
            return postDao.save(entity);
        }

    }

    @Override
    public Integer update(Post entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IPostDAO postDao = session.getMapper(IPostDAO.class);
            return postDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IPostDAO postDao = session.getMapper(IPostDAO.class);
            postDao.removeById(id);
        }
    }

    @Override
    public void incrementLikeQuantity(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IPostDAO postDao = session.getMapper(IPostDAO.class);
            postDao.incrementLikeQuantity(id);
        }   
    }

}
