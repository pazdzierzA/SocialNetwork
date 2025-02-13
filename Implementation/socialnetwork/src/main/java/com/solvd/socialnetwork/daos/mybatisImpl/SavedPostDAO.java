package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.models.SavedPost;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class SavedPostDAO implements ISavedPostDAO{

    @Override
    public SavedPost getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ISavedPostDAO savedPostDao = session.getMapper(ISavedPostDAO.class);
            return savedPostDao.getById(id);
        }
    }

    @Override
    public SavedPost save(SavedPost entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ISavedPostDAO savedPostDao = session.getMapper(ISavedPostDAO.class);
            return savedPostDao.save(entity);
        }
    }

    @Override
    public SavedPost update(SavedPost entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ISavedPostDAO savedPostDao = session.getMapper(ISavedPostDAO.class);
            return savedPostDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ISavedPostDAO savedPostDao = session.getMapper(ISavedPostDAO.class);
            savedPostDao.removeById(id);
        }
        
    }

    @Override
    public List<SavedPost> getByUserId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ISavedPostDAO savedPostDao = session.getMapper(ISavedPostDAO.class);
            return savedPostDao.getByUserId(id);
        }
    }
}
