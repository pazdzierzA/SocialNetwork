package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Message;

public interface IMessageDAO extends IDAO <Message>{
	Message getById (Long id);
	Message save (Message entity);
	Message update (Message entity);
	void removeById(Long id);
}
