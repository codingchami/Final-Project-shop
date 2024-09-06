package com.dev.pos.bo.custom;

import com.dev.pos.bo.SuperBo;
import com.dev.pos.dto.BatchDTO;

import java.sql.SQLException;

public interface BatchBo extends SuperBo {
    public boolean saveBatch(BatchDTO dto) throws SQLException, ClassNotFoundException;
}
