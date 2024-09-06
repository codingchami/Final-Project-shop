package com.dev.pos.bo.impl;

import com.dev.pos.Entity.Batch;
import com.dev.pos.Enum.DaoType;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.BatchDao;
import com.dev.pos.dto.BatchDTO;

import java.sql.SQLException;

public class BatchBoImpl implements BatchBo {

    BatchDao batchDao = DaoFactory.getInstance().getDao(DaoType.BATCH);
    @Override
    public boolean saveBatch(BatchDTO dto) throws SQLException, ClassNotFoundException {
        return batchDao.save(new Batch(
                dto.getCode(),
                dto.getBarcode(),
                dto.getQtyOnHand(),
                dto.getSellingPrice(),
                dto.isAvailable(),
                dto.getShowPrice(),
                dto.getBuyingPrice(),
                dto.getProductCode()
        ));
    }
}
