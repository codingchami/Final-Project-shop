package com.dev.pos.bo.impl;

import com.dev.pos.Entity.User;
import com.dev.pos.Enum.DaoType;
import com.dev.pos.bo.custom.UserBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.UserDao;
import com.dev.pos.dto.UserDTO;

import java.sql.SQLException;

public class UserBoImpl implements UserBo {

    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException {

        return userDao.save(
                new User(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
    }

    @Override
    public UserDTO findUser(String email) throws SQLException, ClassNotFoundException {
        User user = userDao.find(email);
        if(user!=null){
            return new UserDTO(
                    user.getEmail(),
                    user.getPassword()
            );
        }
        return null;
    }
}
