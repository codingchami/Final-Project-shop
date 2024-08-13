package com.dev.pos.controller;

import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.CustomerDTO;
import com.dev.pos.dto.tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableView<CustomerTm> tblCustomer;
    public TableColumn<CustomerTm,Integer> colNo;
    public TableColumn<CustomerTm,String> colName;
    public TableColumn<CustomerTm,String> colEmail;
    public TableColumn<CustomerTm,String> colContact;
    public TableColumn<CustomerTm,Double> colSalary;
    public TableColumn<CustomerTm, Button> colDelete;

    String SearchText ="";

    public void initialize(){
        colNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadCustomer(SearchText);
    }




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

    private void loadCustomer(String SearchText) {
        ObservableList<CustomerTm> oblist = FXCollections.observableArrayList();
        try{
            int counter = 1;
            for(CustomerDTO dto : DatabaseAccessCode.searchCustomer(SearchText)){
                Button button = new Button("Delete");
                CustomerTm customerTm = new CustomerTm(
                        counter,
                        dto.getEmail(),
                        dto.getName(),
                        dto.getContact(),
                        dto.getSalary(),
                        button

                );
                counter++;
                oblist.add(customerTm);

            }
            tblCustomer.setItems(oblist);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

}
