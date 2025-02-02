package com.solvd.socialnetwork.daos;

import java.time.LocalDate;
import java.util.List;

import com.solvd.socialnetwork.models.User;

public interface IUserDAO extends IDAO<User>{
	User getByLogin(String login);
	User getByEmail(String email);
	List<User> getByFirstName(String firstName);
	List<User> getByLastName(String lastName);
	List<User> getByBirthDate(LocalDate birthDate);
}
