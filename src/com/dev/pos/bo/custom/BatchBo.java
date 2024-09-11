package com.dev.pos.bo.custom;

import com.dev.pos.bo.SuperBo;
import com.dev.pos.dto.BatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface BatchBo extends SuperBo {
    public boolean saveBatch(BatchDTO dto) throws SQLException, ClassNotFoundException;

    public List<BatchDTO> findAllBatch(int productCode) throws SQLException, ClassNotFoundException;

    public BatchDTO findBatch(String code) throws SQLException, ClassNotFoundException;
}
