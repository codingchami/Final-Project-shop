package com.dev.pos.controller;

import com.dev.pos.db.DBConnection;
import com.dev.pos.util.security.PasswordManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
    }

    public void btnSignupOnAction(ActionEvent actionEvent) throws IOException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql ="SELECT * FROM user WHERE email =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(PasswordManager.checkPassword(txtPassword.getText().trim(),resultSet.getString("password")));
                System.out.println("Done");
            }else{
                new Alert(Alert.AlertType.ERROR,"User not found...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
