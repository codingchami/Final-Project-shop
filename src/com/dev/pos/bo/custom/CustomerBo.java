package com.dev.pos.bo.custom;

import com.dev.pos.bo.SuperBo;
import com.dev.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException;

    public CustomerDTO findCustomer(String email) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> searchCustomer(String value) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> findAllCustomers() throws SQLException, ClassNotFoundException;



}
