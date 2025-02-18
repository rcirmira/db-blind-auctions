package com.db.system.user.dao;

import com.db.system.data.model.user.User;

public interface UserMapper {
    User getUserByName(String name);
    void insertUser(User user);
}
