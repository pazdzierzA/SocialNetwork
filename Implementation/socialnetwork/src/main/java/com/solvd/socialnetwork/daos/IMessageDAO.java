package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.Message;

public interface IMessageDAO extends IDAO <Message>{
	List<Message> getByUserId(Long id);

}
