package com.dev.pos.dao;

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

    //...............User..........Start...........

    public static boolean createUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO user VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userDTO.getEmail());
            preparedStatement.setString(2, PasswordManager.encrypt(userDTO.getPassword()));

            return preparedStatement.executeUpdate()>0;

    }

    public static UserDTO findUser(String email) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnection.getInstance().getConnection();
            String sql ="SELECT * FROM user WHERE email =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

//                UserDTO userDTO = new UserDTO();
//                userDTO.setEmail(resultSet.getString(1));
//                userDTO.setPassword(resultSet.getString(2));
//
//                return userDTO;

                return new UserDTO(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
            }
            return null;
    }

    //...............User..........End.............

//    ...............Customer ....Start........

    public static boolean createCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, dto.getEmail());
        statement.setString(2, dto.getName());
        statement.setString(3, dto.getContact());
        statement.setDouble(4, dto.getSalary());

        return statement.executeUpdate()>0;

    }

    public static boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name = ?,contact = ?,salary = ? WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dto.getName());
        statement.setString(2, dto.getContact());
        statement.setDouble(3,dto.getSalary());
        statement.setString(4, dto.getEmail());
        return statement.executeUpdate()>0;

    }
    public static boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,email);
        return statement.executeUpdate()>0;

    }

    public static CustomerDTO findCustomer(String email) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE email =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,email);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    public static List<CustomerDTO> findAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        while (resultSet.next()){
            customerDTOList.add(
                    new CustomerDTO(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );

        }

        return customerDTOList;
    }

    public static List<CustomerDTO> searchCustomer(String SearchText) throws SQLException, ClassNotFoundException {

        SearchText = "%"+SearchText+"%";
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE email LIKE ? || name LIKE ? || contact LIKE ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,SearchText);
        statement.setString(2,SearchText);
        statement.setString(3,SearchText);


        ResultSet resultSet = statement.executeQuery();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        while(resultSet.next()){
            customerDTOList.add(new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }

        return customerDTOList;
    }

//    ...............Customer......End..........


//................Product.........Start..............

    public static int getLastProductId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT code FROM product ORDER BY code DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1)+1;
        }
        return 1;
    }

    public static boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
         Connection connection = DBConnection.getInstance().getConnection();
         String sql ="INSERT INTO product VALUES(?,?)";
         PreparedStatement statement = connection.prepareStatement(sql);
         statement.setInt(1,dto.getCode());
         statement.setString(2,dto.getDescription());
         return statement.executeUpdate()>0;
    }

    public static boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE product SET description=? WHERE code =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,dto.getDescription());
        statement.setInt(2,dto.getCode());
        return statement.executeUpdate()>0;

    }

    public static boolean deleteProduct(int code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM product WHERE code=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,code);
        return statement.executeUpdate()>0;
    }

    public ProductDTO findProduct(int code) throws SQLException, ClassNotFoundException {
         Connection connection = DBConnection.getInstance().getConnection();
         String sql = "SELECT * FROM product WHERE code=?";
         PreparedStatement statement = connection.prepareStatement(sql);
         statement.setInt(1,code);
         ResultSet resultSet = statement.executeQuery();

         if(resultSet.next()){
             return new ProductDTO(
                     resultSet.getInt(1),
                     resultSet.getString(2)
             );
         }
         return null;
    }
    
    public List<ProductDTO> findAllProduct() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<ProductDTO> productDTOList = new ArrayList<>();

        while (resultSet.next()){
            productDTOList.add(new ProductDTO(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }

        return productDTOList;

    }

    public List<ProductDTO> searchProduct(String searchText) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product WHERE description LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,searchText);
        ResultSet resultSet = statement.executeQuery();

        List<ProductDTO> productDTOList = new ArrayList<>();

        while(resultSet.next()){
            productDTOList.add(new ProductDTO(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        return productDTOList;
    }







//................Product.........End.................

}
