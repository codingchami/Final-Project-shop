package com.dev.pos.bo.custom;

import com.dev.pos.Entity.Product;
import com.dev.pos.dto.ProductDTO;

import java.util.List;

public interface ProductBo {

    public boolean saveProduct(ProductDTO dto);

    public boolean updateProduct(ProductDTO dto);

    public boolean deleteProduct(int code);

    public ProductDTO findProduct(int code);

    public List<ProductDTO> findAllProducts();

    public List<Product> searchByDescription(String value);

    public int getLastProductId();
}
