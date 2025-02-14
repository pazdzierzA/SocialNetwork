package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class NotificationDAO implements INotificationDAO{

    @Override
    public Notification getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            INotificationDAO notificationDao = session.getMapper(INotificationDAO.class);
            return notificationDao.getById(id);
        }

    }

    @Override
    public Integer save(Notification entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            INotificationDAO notificationDao = session.getMapper(INotificationDAO.class);
            return notificationDao.save(entity);
        }

    }

    @Override
    public Integer update(Notification entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            INotificationDAO notificationDao = session.getMapper(INotificationDAO.class);
            return notificationDao.update(entity);
        }
      
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            INotificationDAO notificationDao = session.getMapper(INotificationDAO.class);
            notificationDao.removeById(id);
        }

    }

    @Override
    public List<Notification> getByType(NotificationType type) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            INotificationDAO notificationDao = session.getMapper(INotificationDAO.class);
            return notificationDao.getByType(type);
        }
    }

}
