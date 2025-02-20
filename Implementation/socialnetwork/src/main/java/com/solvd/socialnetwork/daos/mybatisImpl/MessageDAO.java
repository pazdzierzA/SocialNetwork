package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;
import com.solvd.socialnetwork.daos.IMessageDAO;
import com.solvd.socialnetwork.models.Message;

public class MessageDAO extends AbstractMyBatisDAO implements IMessageDAO {
    private IMessageDAO messageDAO;

    @Override
    public Message getById(Long id) {
        return execute(session -> {
            messageDAO = getMapper(IMessageDAO.class, session);
            return messageDAO.getById(id);
        });

    }

    @Override
    public Integer save(Message entity) {
        return execute(session -> {
            messageDAO = getMapper(IMessageDAO.class, session);
            return messageDAO.save(entity);
        });

    }

    @Override
    public Integer update(Message entity) {
        return execute(session -> {
            messageDAO = getMapper(IMessageDAO.class, session);
            return messageDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            messageDAO = getMapper(IMessageDAO.class, session);
            messageDAO.removeById(id);
        });

    }

    @Override
    public List<Message> getByUserId(Long id) {
        return execute(session -> {
            messageDAO = getMapper(IMessageDAO.class, session);
            return messageDAO.getByUserId(id);
        });

    }

}
