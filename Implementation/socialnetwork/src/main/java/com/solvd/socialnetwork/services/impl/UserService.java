package com.solvd.socialnetwork.services.impl;

import java.time.LocalDate;
import java.util.List;

import com.solvd.socialnetwork.daos.IUserDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.UserDAO;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.IUserService;

public class UserService implements IUserService {
	private IUserDAO userDAO =new UserDAO();
	@Override
	public User getById(Long id) {
		return userDAO.getById(id);
	}

	@Override
	public User save(User entity) {
		return userDAO.save(entity);
	}

	@Override
	public User update(User entity) {
		return userDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		userDAO.removeById(id);
		
	}

	@Override
	public User getByLogin(String login) {
		return userDAO.getByLogin(login);
	}

	@Override
	public User getByEmail(String email) {
		return userDAO.getByEmail(email);
	}

	@Override
	public List<User> getByFirstName(String firstName) {
		return userDAO.getByFirstName(firstName);
	}

	@Override
	public List<User> getByLastName(String lastName) {
		return userDAO.getByLastName(lastName);
	}

	@Override
	public List<User> getByBirthDate(LocalDate birthDate) {
		return userDAO.getByBirthDate(birthDate);
	}

}
