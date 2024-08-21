package com.dev.pos.dao.custom;

import com.dev.pos.Entity.Product;
import com.dev.pos.dao.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<Product,Integer> {

    public int getLastProductId() throws SQLException, ClassNotFoundException;

    public List<Product> searchByDescription(String value) throws SQLException, ClassNotFoundException;

}
