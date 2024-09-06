package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.dto.BatchDTO;
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
import org.apache.commons.codec.binary.Base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

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

    BatchBo batchbo = BoFactory.getInstance().getBo(BoType.BATCH);
    String uniqueData = null;
    BufferedImage bufferedImage = null;

    public void initialize() throws WriterException {
        setQRcode();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage,"png",baos);
        byte[] arr = baos.toByteArray();
            String barcode = Base64.encodeBase64String(arr);




        BatchDTO dto = new BatchDTO(
                uniqueData,
                barcode,
                Integer.parseInt(txtQTY.getText()),
                Double.parseDouble(txtSellingPrice.getText()),
                rdYes.isSelected()?true:false,
                Double.parseDouble(txtShowPrice.getText()),
                Double.parseDouble(txtBuyingPrice.getText()),
                Integer.parseInt(txtProductCode.getText())
            );

        batchbo.saveBatch(dto);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setQRcode() throws WriterException {

        uniqueData = QRdataGenerator.generate(30);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        bufferedImage = MatrixToImageWriter.toBufferedImage(
                qrCodeWriter.encode(
                        uniqueData,
                        BarcodeFormat.QR_CODE,
                        255,230
                )
        );

        Image image  = SwingFXUtils.toFXImage(bufferedImage,null);
        imgQR.setImage(image);
    }

    public void setProductCode(int code,String description){
        txtProductCode.setText(String.valueOf(code));
        txtDescription.setText(description);
    }


}
