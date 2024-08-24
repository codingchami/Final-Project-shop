package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Customer;
import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.CustomerDao;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        return CrudUtil.execute(
                sql,
                customer.getEmail(),
                customer.getName(),
                customer.getContact(),
                customer.getSalary()
        );

    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE customer SET name = ?,contact = ?,salary = ? WHERE email = ?";
        return CrudUtil.execute(
                sql,
                customer.getName(),
                customer.getContact(),
                customer.getSalary(),
                customer.getEmail());
    }

    @Override
    public boolean delete(String email) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE email = ?";
        return CrudUtil.execute(sql,email);
    }

    @Override
    public Customer find(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE email =?";
        ResultSet resultSet = CrudUtil.execute(sql, email);

        if(resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer ";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Customer> customerList = new ArrayList<>();
        while(resultSet.next()){
            customerList.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return customerList;

    }

    @Override
    public List<Customer> search(String value) throws  SQLException, ClassNotFoundException {
        value = "%"+value+"%";

        String sql = "SELECT * FROM customer WHERE email LIKE ? || name LIKE ? || contact LIKE ? ";

        ResultSet resultSet = CrudUtil.execute(sql,value,value,value);
        List<Customer> customerList = new ArrayList<>();

        while(resultSet.next()){
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }

        return customerList;
    }
}
