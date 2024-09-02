package com.dev.pos.bo.custom;

import com.dev.pos.Entity.Product;
import com.dev.pos.bo.SuperBo;
import com.dev.pos.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductBo extends SuperBo {

    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException;

    public ProductDTO findProduct(int code) throws SQLException, ClassNotFoundException;

    public List<ProductDTO> findAllProducts() throws SQLException, ClassNotFoundException;

    public List<ProductDTO> searchByDescription(String value) throws SQLException, ClassNotFoundException;

    public int getLastProductId() throws SQLException, ClassNotFoundException;
}
