package com.solvd.socialnetwork.services;

import java.time.LocalDate;
import java.util.List;

import com.solvd.socialnetwork.models.User;

public interface IUserService extends IService<User>{
	User getByLogin(String login);
	User getByEmail(String email);
	List<User> getByFirstName(String firstName);
	List<User> getByLastName(String lastName);
	List<User> getByBirthDate(LocalDate birthDate);
}