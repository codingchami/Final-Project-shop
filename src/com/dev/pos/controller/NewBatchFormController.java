package com.dev.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NewBatchFormController {
    public AnchorPane context;
    public JFXTextField txtQTY;
    public ImageView imgQR;
    public JFXTextField txtBuyingPrice;
    public JFXTextField txtSellingPrice;
    public JFXTextField txtShowPrice;
    public JFXRadioButton rdNo;
    public ToggleGroup discount;
    public JFXRadioButton rdYes;
    public JFXTextField txtProductCode;
    public JFXTextArea txtDescription;
    public JFXButton btnSave;

    public void initialize(){
        setQRcode();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    private void setQRcode(){

    }


}
