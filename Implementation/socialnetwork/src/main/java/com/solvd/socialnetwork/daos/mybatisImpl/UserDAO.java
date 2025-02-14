package com.solvd.socialnetwork.daos.mybatisImpl;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.solvd.socialnetwork.daos.IUserDAO;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class UserDAO implements IUserDAO {

    @Override
    public User getById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
           
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getById(id);
        }
    }

    @Override
    public Integer save(User entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.save(entity);
        }
      
    }

    @Override
    public Integer update(User entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.update(entity);
        }
       
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            userDao.removeById(id);
        }

    }

    @Override
    public User getByLogin(String login) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getByLogin(login);
        }
    }

    @Override
    public User getByEmail(String email) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getByEmail(email);
        }
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getByFirstName(firstName);
        }
    }

    @Override
    public List<User> getByLastName(String lastName) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getByLastName(lastName);
        }

    }

    @Override
    public List<User> getByBirthDate(LocalDate birthDate) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            IUserDAO userDao = session.getMapper(IUserDAO.class);
            return userDao.getByBirthDate(birthDate);
        }

    }
}
