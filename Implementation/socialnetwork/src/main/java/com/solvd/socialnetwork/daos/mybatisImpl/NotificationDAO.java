package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;
import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;

public class NotificationDAO extends AbstractMyBatisDAO implements INotificationDAO {
    private INotificationDAO notificationDAO;

    @Override
    public Notification getById(Long id) {
        return execute(session -> {
            notificationDAO = getMapper(INotificationDAO.class, session);
            return notificationDAO.getById(id);
        });

    }

    @Override
    public Integer save(Notification entity) {
        return execute(session -> {
            notificationDAO = getMapper(INotificationDAO.class, session);
            return notificationDAO.save(entity);
        });

    }

    @Override
    public Integer update(Notification entity) {
        return execute(session -> {
            notificationDAO = getMapper(INotificationDAO.class, session);
            return notificationDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            notificationDAO = getMapper(INotificationDAO.class, session);
            notificationDAO.removeById(id);
        });

    }

    @Override
    public List<Notification> getByType(NotificationType type) {
        return execute(session -> {
            notificationDAO = getMapper(INotificationDAO.class, session);
            return notificationDAO.getByType(type);
        });
    }

}
