package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;

public interface INotificationDAO extends IDAO <Notification> {
	List<Notification> getByType(NotificationType type);

}
