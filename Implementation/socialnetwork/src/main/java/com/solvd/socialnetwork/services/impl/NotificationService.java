package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.daos.mySQLImpl.NotificationDAO;
import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;
import com.solvd.socialnetwork.services.INotificationService;

public class NotificationService implements INotificationService {
	private INotificationDAO notificationDAO = new NotificationDAO();
	@Override
	public Notification getById(Long id) {
		return notificationDAO.getById(id);
	}

	@Override
	public Notification save(Notification entity) {
		return notificationDAO.save(entity);
	}

	@Override
	public Notification update(Notification entity) {
		return notificationDAO.update(entity);
	}

	@Override
	public void removeById(Long id) {
			notificationDAO.removeById(id);
		
	}

	@Override
	public List<Notification> getByType(NotificationType type) {
		return notificationDAO.getByType(type);
	}

}
