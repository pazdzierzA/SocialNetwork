package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IMessageDAO;
import com.solvd.socialnetwork.models.Message;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class MessageDAO implements IMessageDAO{

    @Override
    public Message getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IMessageDAO messageDao = session.getMapper(IMessageDAO.class);
            return messageDao.getById(id);
        }
      
    }

    @Override
    public Integer save(Message entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IMessageDAO messageDao = session.getMapper(IMessageDAO.class);
            return messageDao.save(entity);
        }
  
    }

    @Override
    public Integer update(Message entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IMessageDAO messageDao = session.getMapper(IMessageDAO.class);
            return messageDao.update(entity);
        }
   
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IMessageDAO messageDao = session.getMapper(IMessageDAO.class);
            messageDao.removeById(id);
        }

    }

    @Override
    public List<Message> getByUserId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IMessageDAO messageDao = session.getMapper(IMessageDAO.class);
            return messageDao.getByUserId(id);
        }

    }
    

}
