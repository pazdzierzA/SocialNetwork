package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.models.SavedPost;

public class SavedPostDAO extends AbstractMyBatisDAO implements ISavedPostDAO{
    private ISavedPostDAO savedPostDAO;
    @Override
    public SavedPost getById(Long id) {
        return execute(session -> {
            savedPostDAO = getMapper(ISavedPostDAO.class, session);
            return savedPostDAO.getById(id);
        });
    }

    @Override
    public Integer save(SavedPost entity) {
        return execute(session -> {
            savedPostDAO = getMapper(ISavedPostDAO.class, session);
            return savedPostDAO.save(entity);
        });
    }

    @Override
    public Integer update(SavedPost entity) {
        return execute(session -> {
            savedPostDAO = getMapper(ISavedPostDAO.class, session);
            return savedPostDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            savedPostDAO = getMapper(ISavedPostDAO.class, session);
            savedPostDAO.removeById(id);
        });
        
    }

    @Override
    public List<SavedPost> getByUserId(Long id) {
        return execute(session -> {
            savedPostDAO = getMapper(ISavedPostDAO.class, session);
            return savedPostDAO.getByUserId(id);
        });
    }
}
