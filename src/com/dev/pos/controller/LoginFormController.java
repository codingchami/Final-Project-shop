package com.dev.pos.controller;

import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.db.DBConnection;
import com.dev.pos.dto.UserDTO;
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

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        try {
            UserDTO user = new DatabaseAccessCode().findUser(txtEmail.getText());

            if(user!=null){
                if(PasswordManager.checkPassword(txtPassword.getText(),user.getPassword())){
                    setUI("DashboardForm");
                }else{
                    new Alert(Alert.AlertType.INFORMATION,"User Not Found.....!").show();
                }
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


    }

    public void btnSignupOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
