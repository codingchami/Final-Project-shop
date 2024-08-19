package com.dev.pos.dao.custom;

import com.dev.pos.Entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException;

    public Customer findCustomer(String email) throws SQLException, ClassNotFoundException;

    public List<Customer> findAllCustomer() throws SQLException, ClassNotFoundException;

    public List<Customer> searchCustomer(String value) throws SQLException, ClassNotFoundException;




}
