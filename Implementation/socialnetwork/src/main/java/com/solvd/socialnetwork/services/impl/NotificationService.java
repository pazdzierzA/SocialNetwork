package com.solvd.socialnetwork.services.impl;

import java.util.List;

import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;
import com.solvd.socialnetwork.services.INotificationService;

public class NotificationService extends BaseService implements INotificationService {
	private INotificationDAO notificationDAO;

	public NotificationService() {
		super();
		this.notificationDAO = daoFactory.getNotificationDAO();
	}
	@Override
	public Notification getById(Long id) {
		return notificationDAO.getById(id);
	}

	@Override
	public Integer save(Notification entity) {
		return notificationDAO.save(entity);
	}

	@Override
	public Integer update(Notification entity) {
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
