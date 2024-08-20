package com.dev.pos.dao;

import com.dev.pos.Entity.Customer;
import com.dev.pos.Entity.User;
import com.dev.pos.dao.custom.CustomerDao;
import com.dev.pos.dao.custom.ProductDao;
import com.dev.pos.dao.custom.UserDao;
import com.dev.pos.dao.impl.CustomerDaoImpl;
import com.dev.pos.dao.impl.ProductDaoImpl;
import com.dev.pos.dao.impl.UserDaoImpl;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.CustomerDTO;
import com.dev.pos.dto.ProductDTO;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.security.PasswordManager;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    CustomerDao customerDao = new CustomerDaoImpl();
    UserDao userDao = new UserDaoImpl();
    ProductDao productDao = new ProductDaoImpl();

    //...............User..........Start...........

    public  boolean createUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDao.saveUser(
                new User(
                     userDTO.getEmail(),
                     userDTO.getPassword()
                )
        );
    }

    public  UserDTO findUser(String email) throws SQLException, ClassNotFoundException {
        User user = userDao.findUser(email);
        if(user!=null){
            return new UserDTO(
                    user.getEmail(),
                    user.getPassword()
            );
        }
        return null;
    }

    //...............User..........End.............

//    ...............Customer ....Start........

    public boolean createCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDao.saveCustomer(
                new Customer(
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary()
                )
        );


    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDao.updateCustomer(
                new Customer(
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary()
                )
        );

    }
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {
        return customerDao.deleteCustomer(email);

    }

    public CustomerDTO findCustomer(String email) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.findCustomer(email);
        if(customer!=null){
            return new CustomerDTO(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            );
        }
        return null;
    }

    public List<CustomerDTO> findAllCustomer() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer c:customerDao.findAllCustomer()){
            customerDTOS.add(
                    new CustomerDTO(
                            c.getEmail(),
                            c.getName(),
                            c.getContact(),
                            c.getSalary()
                    )
            );
        }
        return customerDTOS;

    }

    public List<CustomerDTO> searchCustomer(String SearchText) throws SQLException, ClassNotFoundException {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer c: customerDao.searchCustomer(SearchText)){
            customerDTOList.add(
                    new CustomerDTO(
                            c.getEmail(),
                            c.getName(),
                            c.getContact(),
                            c.getSalary()
                    )
            );
        }
        return customerDTOList;

    }

//    ...............Customer......End..........


//................Product.........Start..............

    public static int getLastProductId() throws SQLException, ClassNotFoundException {

    }

    public static boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {

    }

    public static boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {


    }

    public static boolean deleteProduct(int code) throws SQLException, ClassNotFoundException {

    }

    public ProductDTO findProduct(int code) throws SQLException, ClassNotFoundException {

    }

    public List<ProductDTO> findAllProduct() throws SQLException, ClassNotFoundException {


    }

    public List<ProductDTO> searchProduct(String searchText) throws SQLException, ClassNotFoundException {

    }







//................Product.........End.................

}
