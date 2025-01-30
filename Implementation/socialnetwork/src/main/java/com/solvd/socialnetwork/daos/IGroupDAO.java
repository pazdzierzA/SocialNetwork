package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Group;

public interface IGroupDAO extends IDAO <Group>{
	Group getById (Long id);
	Group save (Group entity);
	Group update (Group entity);
	void removeById(Long id);

}
