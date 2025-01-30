package com.solvd.socialnetwork.daos;

import com.solvd.socialnetwork.models.Like;

public interface ILikeDAO extends IDAO <Like>{
	Like getById (Long id);
	Like save (Like entity);
	Like update (Like entity);
	void removeById(Long id);
}
