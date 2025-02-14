package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.IMessageDAO;
import com.solvd.socialnetwork.daos.mybatisImpl.MessageDAO;
import com.solvd.socialnetwork.models.Message;
import com.solvd.socialnetwork.services.IMessageService;

public class MessageService implements IMessageService {

	private IMessageDAO messageDAO = new MessageDAO();
	@Override
	public Message getById(Long id) {

		return messageDAO.getById(id);
	}

	@Override
	public Integer save(Message entity) {
		return messageDAO.save(entity);
	}

	@Override
	public Integer update(Message entity) {

		return messageDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
		messageDAO.removeById(id);
		
	}

	@Override
	public List<Message> getByUserId(Long id) {
		return messageDAO.getByUserId(id);
	}

}
