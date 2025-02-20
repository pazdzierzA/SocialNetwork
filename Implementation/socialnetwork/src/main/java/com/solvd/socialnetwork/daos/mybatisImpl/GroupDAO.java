package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;
import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.models.Group;

public class GroupDAO extends AbstractMyBatisDAO implements IGroupDAO {
    private IGroupDAO groupDAO;

    @Override
    public Group getById(Long id) {
        return execute(session -> {
            groupDAO = getMapper(IGroupDAO.class, session);
            return groupDAO.getById(id);
        });

    }

    @Override
    public Integer save(Group entity) {
        return execute(session -> {
            groupDAO = getMapper(IGroupDAO.class, session);
            return groupDAO.save(entity);
        });

    }

    @Override
    public Integer update(Group entity) {
        return execute(session -> {
            groupDAO = getMapper(IGroupDAO.class, session);
            return groupDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            groupDAO = getMapper(IGroupDAO.class, session);
            groupDAO.removeById(id);
        });
    }

    @Override
    public List<Group> getByName(String name) {
        return execute(session -> {
            groupDAO = getMapper(IGroupDAO.class, session);
            return groupDAO.getByName(name);
        });
    }

}
