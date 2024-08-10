package com.dev.pos.dao;

import com.dev.pos.db.DBConnection;
import com.dev.pos.util.security.PasswordManager;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccessCode {

    //...............User..........Start...........

//    public static boolean createUser(String email,String password) {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "INSERT INTO user VALUES(?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, txtEmail.getText());
//        preparedStatement.setString(2, PasswordManager.encrypt(txtPassword.getText().trim()));
//
//
//    }

    //...............User..........End.............
}
