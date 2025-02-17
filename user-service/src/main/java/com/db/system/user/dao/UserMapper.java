package com.db.system.user.dao;

import com.db.system.user.data.model.User;

public interface UserMapper {
    User getUserByName(String name);
    void insertUser(User user);
}
