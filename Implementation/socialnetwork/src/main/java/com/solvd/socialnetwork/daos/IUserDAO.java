package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.User;

public interface IUserDAO extends IDAO<User>{
	User getById (Long id);
	User save (User entity);
	User update (User entity);
	void removeById(Long id);
}
