package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;
import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.enums.FriendshipStatus;
import com.solvd.socialnetwork.enums.FriendshipType;
import com.solvd.socialnetwork.models.Friendship;

public class FriendshipDAO extends AbstractMyBatisDAO implements IFriendshipDAO {
    private IFriendshipDAO friendshipDAO;

    @Override
    public Friendship getById(Long id) {
        return execute(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            return friendshipDAO.getById(id);
        });
    }

    @Override
    public Integer save(Friendship entity) {
        return execute(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            return friendshipDAO.save(entity);
        });
    }

    @Override
    public Integer update(Friendship entity) {
        return execute(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            return friendshipDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            friendshipDAO.removeById(id);
        });
    }

    @Override
    public List<Friendship> getByType(FriendshipType type) {
        return execute(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            return friendshipDAO.getByType(type);
        });

    }

    @Override
    public List<Friendship> getByStatus(FriendshipStatus status) {
        return execute(session -> {
            friendshipDAO = getMapper(IFriendshipDAO.class, session);
            return friendshipDAO.getByStatus(status);
        });
    }

}
