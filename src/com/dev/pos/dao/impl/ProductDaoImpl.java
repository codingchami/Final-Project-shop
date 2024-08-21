package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Product;
import com.dev.pos.dao.custom.ProductDao;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="INSERT INTO product VALUES(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,product.getCode());
        statement.setString(2,product.getDescription());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE product SET description=? WHERE code =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,product.getDescription());
        statement.setInt(2,product.getCode());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean delete(Integer code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM product WHERE code=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,code);
        return statement.executeUpdate()>0;
    }

    @Override
    public Product find(Integer code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product WHERE code=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,code);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }



    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (resultSet.next()){
            productList.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }

        return productList;


    }

    @Override
    public List<Product> search(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }


//    public List<Product> searchProduct(String value) throws SQLException, ClassNotFoundException {
//        value="%"+value+"%";
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM product WHERE description LIKE ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,value);
//        ResultSet resultSet = statement.executeQuery();
//
//        List<Product> productList = new ArrayList<>();
//
//        while(resultSet.next()){
//            productList.add(new Product(
//                    resultSet.getInt(1),
//                    resultSet.getString(2)
//            ));
//        }
//        return productList;
//    }

    @Override
    public int getLastProductId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT code FROM product ORDER BY code DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1)+1;
        }
        return 1;
    }

    @Override
    public List<Product> searchByDescription(String value) throws SQLException, ClassNotFoundException {
        value="%"+value+"%";
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product WHERE description LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,value);
        ResultSet resultSet = statement.executeQuery();

        List<Product> productList = new ArrayList<>();

        while(resultSet.next()){
            productList.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
        return productList;
    }
}
