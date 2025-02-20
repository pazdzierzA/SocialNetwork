package com.solvd.socialnetwork.daos.mybatisImpl;

import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.models.Post;

public class PostDAO extends AbstractMyBatisDAO implements IPostDAO {
    private IPostDAO postDAO;

    @Override
    public Post getById(Long id) {
        return execute(session -> {
            postDAO = getMapper(IPostDAO.class, session);
            return postDAO.getById(id);
        });
    }

    @Override
    public Integer save(Post entity) {
        return execute(session -> {
            postDAO = getMapper(IPostDAO.class, session);
            return postDAO.save(entity);
        });

    }

    @Override
    public Integer update(Post entity) {
        return execute(session -> {
            postDAO = getMapper(IPostDAO.class, session);
            return postDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            postDAO = getMapper(IPostDAO.class, session);
            postDAO.removeById(id);
        });
    }

    @Override
    public void incrementLikeQuantity(Long id) {
        executeVoid(session -> {
            postDAO = getMapper(IPostDAO.class, session);
            postDAO.incrementLikeQuantity(id);
        });
    }

}
