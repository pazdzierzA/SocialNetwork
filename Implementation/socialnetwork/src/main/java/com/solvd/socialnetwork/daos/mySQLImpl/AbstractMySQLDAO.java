package com.solvd.socialnetwork.daos.mySQLImpl;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractMySQLDAO<T>  {
	
	protected abstract T getMappedEntity(ResultSet resultSet) throws SQLException;

}
