package com.dev.pos.bo.impl;

import com.dev.pos.Entity.Product;
import com.dev.pos.Enum.DaoType;
import com.dev.pos.bo.custom.ProductBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.ProductDao;
import com.dev.pos.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {

    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    @Override
    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDao.save(
                new Product(
                        dto.getCode(),
                        dto.getDescription()
                )
        );
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDao.update(
                new Product(
                        dto.getCode(),
                        dto.getDescription()
                )
        );
    }

    @Override
    public boolean deleteProduct(int code) throws SQLException, ClassNotFoundException {
        return productDao.delete(code);
    }

    @Override
    public ProductDTO findProduct(int code) throws SQLException, ClassNotFoundException {
        Product product = productDao.find(code);
        if(product!=null){
            return new ProductDTO(
                    product.getCode(),
                    product.getDescription()
            );
        }
        return null;
    }

    @Override
    public List<ProductDTO> findAllProducts() throws SQLException, ClassNotFoundException {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p:productDao.findAll()){
            productDTOList.add(
                    new ProductDTO(
                            p.getCode(),
                            p.getDescription()
                    )
            );
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> searchByDescription(String value) throws SQLException, ClassNotFoundException {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product p : productDao.searchByDescription(value)){
            productDTOList.add(
                    new ProductDTO(
                            p.getCode(),
                            p.getDescription()
                    )
            );
        }
        return productDTOList;
    }

    @Override
    public int getLastProductId() throws SQLException, ClassNotFoundException {
        return productDao.getLastProductId();
    }
}
