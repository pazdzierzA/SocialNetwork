package com.solvd.socialnetwork.daos.mybatisImpl;

import java.time.LocalDate;
import java.util.List;

import com.solvd.socialnetwork.daos.IUserDAO;
import com.solvd.socialnetwork.models.User;

public class UserDAO extends AbstractMyBatisDAO implements IUserDAO {
    private IUserDAO userDAO;

    @Override
    public User getById(Long id) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getById(id);
        });

    }

    @Override
    public Integer save(User entity) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.save(entity);
        });

    }

    @Override
    public Integer update(User entity) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.update(entity);
        });

    }

    @Override
    public void removeById(Long id) {
        executeVoid(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            userDAO.removeById(id);
        });

    }

    @Override
    public User getByLogin(String login) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getByLogin(login);
        });
    }

    @Override
    public User getByEmail(String email) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getByEmail(email);
        });
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getByFirstName(firstName);
        });
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getByLastName(lastName);
        });

    }

    @Override
    public List<User> getByBirthDate(LocalDate birthDate) {
        return execute(session -> {
            userDAO = getMapper(IUserDAO.class, session);
            return userDAO.getByBirthDate(birthDate);
        });

    }
}
