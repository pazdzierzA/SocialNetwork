package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Friendship;

public interface IFriendshipDAO extends IDAO<Friendship>{
	Friendship getById (Long id);
	Friendship save (Friendship entity);
	Friendship update (Friendship entity);
	void removeById(Long id);

}
