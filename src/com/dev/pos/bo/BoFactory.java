package com.dev.pos.bo;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.impl.CustomerBoImpl;
import com.dev.pos.bo.impl.ProductBoImpl;
import com.dev.pos.bo.impl.UserBoImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        if(boFactory==null){
            boFactory = new BoFactory();
        }

        return boFactory;
    }

    public <T> T getBo(BoType boType){
        switch (boType){
            case PRODUCT:
                return (T) new ProductBoImpl();
            case USER:
                return (T) new UserBoImpl();
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            default:
                return null;
        }
    }
}
