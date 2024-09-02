package com.dev.pos.bo.custom;

import com.dev.pos.dto.UserDTO;

import java.sql.SQLException;

public interface UserBo {

    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException;

    public UserDTO findUser(String email) throws SQLException, ClassNotFoundException;


}
