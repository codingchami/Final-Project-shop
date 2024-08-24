package com.dev.pos.dao;

import com.dev.pos.Entity.Customer;
import com.dev.pos.Entity.Product;
import com.dev.pos.Entity.User;
import com.dev.pos.Enum.DaoType;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessCode {

    CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    //...............User..........Start...........

    public  boolean createUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDao.save(
                new User(
                     userDTO.getEmail(),
                     userDTO.getPassword()
                )
        );
    }

    public  UserDTO findUser(String email) throws SQLException, ClassNotFoundException {
        User user = userDao.find(email);
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
        return customerDao.save(
                new Customer(
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary()
                )
        );


    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(
                new Customer(
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary()
                )
        );

    }
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {
        return customerDao.delete(email);

    }

    public CustomerDTO findCustomer(String email) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.find(email);
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
        for(Customer c:customerDao.findAll()){
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
        for(Customer c: customerDao.search(SearchText)){
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

    public int getLastProductId() throws SQLException, ClassNotFoundException {
        return productDao.getLastProductId();
    }

    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDao.save(
                new Product(
                        dto.getCode(),
                        dto.getDescription()
                )
        );
    }

    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDao.update(
                new Product(
                        dto.getCode(),
                        dto.getDescription()
                )
        );

    }

    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException {
        return productDao.delete(code);
    }

    public ProductDTO findProduct(int code) throws SQLException, ClassNotFoundException {
        Product product = productDao.find(code);
        if(product!=null){
            return new ProductDTO(
                    product.getCode(),
                    product.getDescription()
            );
        }
        return null;
    }

    public List<ProductDTO> findAllProduct() throws SQLException, ClassNotFoundException {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p:productDao.findAll()){
            productDTOList.add(
                    new ProductDTO(
                            p.getCode(),
                            p.getDescription()
                    )
            );
        }
        return productDTOList;
    }

    public List<ProductDTO> searchProductByDescription(String searchText) throws SQLException, ClassNotFoundException {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p : productDao.searchByDescription(searchText)){
            productDTOList.add(
                    new ProductDTO(
                            p.getCode(),
                            p.getDescription()
                    )
            );
        }
        return productDTOList;
    }







//................Product.........End.................

}
