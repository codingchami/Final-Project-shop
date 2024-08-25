package com.dev.pos.bo.custom;

import com.dev.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerBo {

    public boolean saveCustomer(CustomerDTO customerDTO);

    public boolean updateCustomer(CustomerDTO customerDTO);

    public boolean deleteCustomer(String email);

    public CustomerDTO findCustomer(String email);

    public List<CustomerDTO> searchCustomer(String value);

    public List<CustomerDTO> findAllCustomers();



}
