package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Notification;

public interface INotificationDAO extends IDAO <Notification> {
	Notification getById (Long id);
	Notification save (Notification entity);
	Notification update (Notification entity);
	void removeById(Long id);
}
