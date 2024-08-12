package com.dev.pos.controller;

import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.CustomerDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerFormController {

    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtName;
    public JFXTextField txtSalary;
    public JFXButton btnSave;
    public JFXTextField txtSearch;
    public TableView tblCustomer;
    public TableColumn colNo;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colDelete;


    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        CustomerDTO dto = new CustomerDTO(
                txtEmail.getText(),
                txtName.getText(),txtContact.getText(),
                Double.valueOf(txtSalary.getText())
        );
try {
    if (btnSave.getText().equalsIgnoreCase("Save Customer")) {

        CustomerDTO customer = DatabaseAccessCode.findCustomer(txtEmail.getText());



        if (customer != null) {
            new Alert(Alert.AlertType.INFORMATION,"Customer is already saved!.......").show();
            }else{
                boolean isSaved = DatabaseAccessCode.createCustomer(dto);

                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Customer has been saved....!").show();
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.INFORMATION,"Something went wrong,Try again.....!").show();
                }
            }
        }
    } catch (ClassNotFoundException | SQLException e){
        e.printStackTrace();

    }

    }



    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
    private void clearFields() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
    }
}
