package com.dev.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ProductMainFormController {
    public AnchorPane context;
    public JFXTextField txtProductCode;
    public JFXTextArea txtProductDescription;
    public JFXButton btnSave;
    public JFXTextField txtSearch;
    public TableView tblProduct;
    public TableColumn colProductid;
    public TableColumn colDescription;
    public TableColumn colShowMore;
    public TableColumn colDelete;
    public JFXTextField txtSelectedProductCode;
    public JFXTextArea txtSelectedDescription;
    public TableView tblProductName;
    public TableColumn colNo;
    public TableColumn colQty;
    public TableColumn colBuyingprice;
    public TableColumn colDiscount;
    public TableColumn colShowPrice;
    public TableColumn colSellingPrice;
    public TableColumn colNameDelete;

    public void btnBackToHome(ActionEvent actionEvent) {
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnAddNewBatchOnAction(ActionEvent actionEvent) {
    }


    public void setUI(String location) throws IOException {
        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
