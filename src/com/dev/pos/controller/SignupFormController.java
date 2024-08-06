package com.dev.pos.controller;

import com.dev.pos.db.DBConnection;
import com.dev.pos.util.security.PasswordManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupFormController {
    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO user VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,txtEmail.getText());
            preparedStatement.setString(2, PasswordManager.encrypt(txtPassword.getText().trim()));

            int count = preparedStatement.executeUpdate();
            if(count>0){
                new Alert(Alert.AlertType.INFORMATION,"User has been saved!..........").show();
                setUI("LoginForm");
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Something went wrong,Try again!..........").show();
            }


        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage().toString()).show();

        }
    }

    public void btnAlreadyHaveOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
