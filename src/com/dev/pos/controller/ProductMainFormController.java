package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.bo.custom.ProductBo;
import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.BatchDTO;
import com.dev.pos.dto.ProductDTO;
import com.dev.pos.dto.tm.BatchTm;
import com.dev.pos.dto.tm.ProductTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductMainFormController {
    public AnchorPane context;
    public JFXTextField txtProductCode;
    public JFXTextArea txtProductDescription;
    public JFXButton btnSave;
    public JFXTextField txtSearch;
    public TableView<ProductTm> tblProduct;
    public TableColumn<ProductTm,Integer> colProductid;
    public TableColumn<ProductTm,String> colDescription;
    public TableColumn<ProductTm,String> colShowMore;
    public TableColumn<ProductTm,Button> colDelete;
    public JFXTextField txtSelectedProductCode;
    public JFXTextArea txtSelectedDescription;
    public TableView<BatchTm> tblProductMain;
    public TableColumn<BatchTm,String> colNo;
    public TableColumn<BatchTm,Integer> colQty;
    public TableColumn<BatchTm,Double> colBuyingprice;
    public TableColumn<BatchTm,String> colDiscount;
    public TableColumn<BatchTm,Double> colShowPrice;
    public TableColumn<BatchTm,Double> colSellingPrice;
    public TableColumn<BatchTm,Button> colNameDelete;
    public JFXButton btnNewBatch;


    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    BatchBo batchBo = BoFactory.getInstance().getBo(BoType.BATCH);
    String searchText = "";
    public void initialize(){
        loadProductId();
        colProductid.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colShowMore.setCellValueFactory(new PropertyValueFactory<>("showMoreBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        colNo.setCellValueFactory(new PropertyValueFactory<>("code"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colBuyingprice.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colShowPrice.setCellValueFactory(new PropertyValueFactory<>("showPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        colNameDelete.setCellValueFactory(new PropertyValueFactory<>("button"));

        loadAllProducts(searchText);

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setData(newValue);
            }
        });

    }
    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(productBo.getLastProductId()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
       try{
           if(btnSave.getText().equalsIgnoreCase("Save Product")){
               boolean isSaved = productBo.saveProduct(new ProductDTO(
                       Integer.valueOf(txtProductCode.getText()),
                       txtProductDescription.getText()
               ));

               if(isSaved){
                   new Alert(Alert.AlertType.INFORMATION,"Product has been saved...!").show();
                   loadAllProducts(searchText);
                   loadProductId();
                   clear();
               }else{
                   new Alert(Alert.AlertType.ERROR,"Something went wrong!....").show();
               }
           }else{
               boolean isUpdated = productBo.updateProduct(new ProductDTO(
                       Integer.valueOf(txtProductCode.getText()),
                       txtProductDescription.getText()
               ));

               if(isUpdated){
                   new Alert(Alert.AlertType.INFORMATION,"Product has been updated...!").show();
                   loadAllProducts(searchText);
                   loadProductId();
                   btnSave.setText("Save Product");
                   clear(); 
               }
           }
       }catch (SQLException | ClassNotFoundException e){
           e.printStackTrace();
       }
    }

    public void btnAddNewBatchOnAction(ActionEvent actionEvent) throws IOException {


        if(txtSelectedProductCode.getText().trim().length()>0){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/NewBatchForm.fxml"));
            Parent parent = fxmlLoader.load();
            NewBatchFormController controller = fxmlLoader.getController();
            Stage stage = new Stage();
            controller.setProductCode(
                    Integer.parseInt(txtSelectedProductCode.getText()),
                    txtSelectedDescription.getText(),
                    stage
            );


            stage.setScene(new Scene(parent));
            stage.show();
            stage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.WARNING,"Please select valid Product....!").show();
        }

    }


    public void setUI(String location) throws IOException {
        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void loadAllProducts(String searchText){
        ObservableList<ProductTm> oblist = FXCollections.observableArrayList();

        try {
            for(ProductDTO p : productBo.findAllProducts()){
                Button showMore = new Button("Show More");
                Button deleteBtn = new Button("Delete");
                ProductTm tm = new ProductTm(
                        p.getCode(),
                        p.getDescription(),
                        showMore,
                        deleteBtn
                );
                oblist.add(tm);
            }

            tblProduct.setItems(oblist);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(ProductTm newValue){

        btnNewBatch.setDisable(false);
        btnSave.setText("Update Product");
        txtProductCode.setText(String.valueOf(newValue.getCode()));
        txtSelectedProductCode.setText(String.valueOf(newValue.getCode()));

        txtProductDescription.setText(newValue.getDescription());
        txtSelectedDescription.setText(newValue.getDescription());

        loadBatchData(newValue.getCode());

    }

    private void clear(){
        txtProductDescription.clear();
        txtSelectedProductCode.clear();
        txtSelectedDescription.clear();
    }

    private void loadBatchData(int code){

        try {
            ObservableList<BatchTm> oblist = FXCollections.observableArrayList();

            if(batchBo.findAllBatch(code)!=null){
                List<BatchDTO> allBatch = batchBo.findAllBatch(code);
                for(BatchDTO b : allBatch){
                    Button button = new Button("Delete");
                    BatchTm tm = new BatchTm(
                            b.getCode(),
                            b.getQtyOnHand(),
                            b.getBuyingPrice(),
                            b.isAvailable(),
                            b.getShowPrice(),
                            b.getSellingPrice(),
                            button
                    );

                    oblist.add(tm);
                }
                tblProductMain.setItems(oblist);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
