package com.solvd.socialnetwork.services.impl;


import com.solvd.socialnetwork.factories.DAOFactoryProvider;
import com.solvd.socialnetwork.factories.IDAOFactory;

public abstract class BaseService {
   
    protected final IDAOFactory daoFactory = DAOFactoryProvider.getDAOFactory();

    public BaseService() {
    }
}