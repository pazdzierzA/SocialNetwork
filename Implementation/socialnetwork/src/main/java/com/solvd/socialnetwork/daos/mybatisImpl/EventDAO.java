package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.models.Event;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class EventDAO implements IEventDAO{

    @Override
    public Event getById(Long id) {
          try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.getById(id);
    }
}

    @Override
    public Event save(Event entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.save(entity);
        }
    }

    @Override
    public Event update(Event entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            eventDao.removeById(id);
        }

    }

    @Override
    public List<Event> getByName(String name) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.getByName(name);
        }
    }

    @Override
    public List<Event> getByCreatorId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.getByCreatorId(id);
        }
    }

    @Override
    public List<Event> getByLocation(String location) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IEventDAO eventDao = session.getMapper(IEventDAO.class);
            return eventDao.getByLocation(location);
        }
    }
    
}
