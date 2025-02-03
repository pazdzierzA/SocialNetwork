package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.Group;

public interface IGroupService extends IService<Group>{
	List<Group> getByName(String name);
}