package com.dev.pos.dao.impl;

import com.dev.pos.Entity.User;
import com.dev.pos.dao.custom.UserDao;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

    @Override
    public User findUser(String email) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
