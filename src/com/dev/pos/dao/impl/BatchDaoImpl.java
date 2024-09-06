package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Batch;
import com.dev.pos.dao.custom.BatchDao;

import java.sql.SQLException;
import java.util.List;

public class BatchDaoImpl implements BatchDao {

    @Override
    public boolean save(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Batch find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Batch> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Batch> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
