package com.solvd.socialnetwork.daos.mybatisImpl;

import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.models.LikePost;

public class LikePostDAO extends AbstractMyBatisDAO implements ILikePostDAO{
    private ILikePostDAO likePostDAO;
    @Override
    public LikePost getById(Long id) {
         return execute(session -> {
            likePostDAO = getMapper(ILikePostDAO.class, session);
            return likePostDAO.getById(id);
        });
    }

    @Override
    public Integer save(LikePost entity) {
        return execute(session -> {
            likePostDAO = getMapper(ILikePostDAO.class, session);
            return likePostDAO.save(entity);
        });
    }

    @Override
    public Integer update(LikePost entity) {
        return execute(session -> {
            likePostDAO = getMapper(ILikePostDAO.class, session);
            return likePostDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            likePostDAO = getMapper(ILikePostDAO.class, session);
            likePostDAO.removeById(id);
        });

    }

    @Override
    public LikePost getByPostId(Long id) {
        return execute(session -> {
            likePostDAO = getMapper(ILikePostDAO.class, session);
            return likePostDAO.getByPostId(id);
        });
    }

    }
