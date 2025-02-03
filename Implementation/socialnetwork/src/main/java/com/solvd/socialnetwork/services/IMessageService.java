package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.Message;

public interface IMessageService extends IService<Message>{
	List<Message> getByUserId(Long id);
}