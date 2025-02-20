package com.solvd.socialnetwork.daos.mybatisImpl;


import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.models.Story;

public class StoryDAO extends AbstractMyBatisDAO implements IStoryDAO {
    private IStoryDAO storyDAO;

    @Override
    public Story getById(Long id) {
        return execute(session -> {
            storyDAO = getMapper(IStoryDAO.class, session);
            return storyDAO.getById(id);
        });
    }

    @Override
    public Integer save(Story entity) {
        return execute(session -> {
            storyDAO = getMapper(IStoryDAO.class, session);
            return storyDAO.save(entity);
        });
    }

    @Override
    public Integer update(Story entity) {
        return execute(session -> {
            storyDAO = getMapper(IStoryDAO.class, session);
            return storyDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            storyDAO = getMapper(IStoryDAO.class, session);
            storyDAO.removeById(id);
        });
    }

    @Override
    public void removeByStoryCreatorId(Long id) {
        executeVoid(session -> {
            storyDAO = getMapper(IStoryDAO.class, session);
            storyDAO.removeByStoryCreatorId(id);
        });
    }
}
