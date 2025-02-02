package com.solvd.socialnetwork.daos;

import java.util.List;

import com.solvd.socialnetwork.models.Group;

public interface IGroupDAO extends IDAO <Group>{
	List<Group> getByName(String name);

}
