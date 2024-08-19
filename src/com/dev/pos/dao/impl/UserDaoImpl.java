package com.dev.pos.dao.impl;

import com.dev.pos.Entity.User;
import com.dev.pos.dao.custom.UserDao;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.security.PasswordManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2, PasswordManager.encrypt(user.getPassword()));

        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public User findUser(String email) throws SQLException, ClassNotFoundException {
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

            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
