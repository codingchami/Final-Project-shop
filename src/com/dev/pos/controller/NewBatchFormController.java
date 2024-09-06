package com.dev.pos.controller;

import com.dev.pos.util.security.qr.QRdataGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;

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

    public void initialize() throws WriterException {
        setQRcode();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    private void setQRcode() throws WriterException {

        String uniqueData = QRdataGenerator.generate(30);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(
                qrCodeWriter.encode(
                        uniqueData,
                        BarcodeFormat.QR_CODE,
                        255,230
                )
        );

        Image image  = SwingFXUtils.toFXImage(bufferedImage,null);
        imgQR.setImage(image);
    }


}
