package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Customer;
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
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, customer.getEmail());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getContact());
        statement.setDouble(4, customer.getSalary());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name = ?,contact = ?,salary = ? WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getContact());
        statement.setDouble(3, customer.getSalary());
        statement.setString(4, customer.getEmail());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,email);
        return statement.executeUpdate()>0;
    }

    @Override
    public Customer findCustomer(String email) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE email =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,email);
        ResultSet resultSet = statement.executeQuery();

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
    public List<Customer> findAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()){
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
    public List<Customer> searchCustomer(String value) throws SQLException, ClassNotFoundException {
        value = "%"+value+"%";
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE email LIKE ? || name LIKE ? || contact LIKE ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,value);
        statement.setString(2,value);
        statement.setString(3,value);


        ResultSet resultSet = statement.executeQuery();
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
