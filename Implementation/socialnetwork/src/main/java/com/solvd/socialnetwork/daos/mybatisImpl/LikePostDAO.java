package com.solvd.socialnetwork.daos.mybatisImpl;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class LikePostDAO implements ILikePostDAO{

    @Override
    public LikePost getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ILikePostDAO likePostDAO = session.getMapper(ILikePostDAO.class);
            return likePostDAO.getById(id);
        }
    }

    @Override
    public LikePost save(LikePost entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ILikePostDAO likePostDAO = session.getMapper(ILikePostDAO.class);
            return likePostDAO.save(entity);
        }
    }

    @Override
    public LikePost update(LikePost entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ILikePostDAO likePostDAO = session.getMapper(ILikePostDAO.class);
            return likePostDAO.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ILikePostDAO likePostDAO = session.getMapper( ILikePostDAO.class);
            likePostDAO.removeById(id);
        }

    }

    @Override
    public LikePost getByPostId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ILikePostDAO likePostDAO = session.getMapper(ILikePostDAO.class);
            return likePostDAO.getByPostId(id);
        }
    }

    }
