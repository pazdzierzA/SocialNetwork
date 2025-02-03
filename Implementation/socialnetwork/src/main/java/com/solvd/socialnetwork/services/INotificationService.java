package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.enums.NotificationType;
import com.solvd.socialnetwork.models.Notification;

public interface INotificationService extends IService<Notification>{
	List<Notification> getByType(NotificationType type);
}