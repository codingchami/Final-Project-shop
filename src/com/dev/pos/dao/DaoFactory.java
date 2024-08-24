package com.dev.pos.dao;

import com.dev.pos.Enum.DaoType;
import com.dev.pos.dao.impl.CustomerDaoImpl;
import com.dev.pos.dao.impl.ProductDaoImpl;
import com.dev.pos.dao.impl.UserDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if(daoFactory==null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDaoImpl();

            case USER:
                return new UserDaoImpl();

            case PRODUCT:
                return new ProductDaoImpl();

            default:
                return null;
        }
    }
}
