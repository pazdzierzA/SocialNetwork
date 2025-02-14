package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.models.EventParticipant;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class EventParticipantDAO implements IEventParticipantDAO {

    @Override
    public EventParticipant getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventParticipantDAO eventParticipantDao = session.getMapper(IEventParticipantDAO.class);
            return eventParticipantDao.getById(id);
        }
    }

    @Override
    public Integer save(EventParticipant entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventParticipantDAO eventParticipantDao = session.getMapper(IEventParticipantDAO.class);
            return eventParticipantDao.save(entity);
        }

    }

    @Override
    public Integer update(EventParticipant entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventParticipantDAO eventParticipantDao = session.getMapper(IEventParticipantDAO.class);
            return eventParticipantDao.update(entity);
        }
    
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventParticipantDAO eventParticipantDao = session.getMapper(IEventParticipantDAO.class);
            eventParticipantDao.removeById(id);
        }

    }

    @Override
    public List<EventParticipant> getByEventId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventParticipantDAO eventParticipantDao = session.getMapper(IEventParticipantDAO.class);
            return eventParticipantDao.getByEventId(id);
        }

    }
    
}
