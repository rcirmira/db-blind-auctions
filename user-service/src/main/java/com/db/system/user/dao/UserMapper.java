package com.db.system.user.dao;

import com.db.system.user.data.model.User;

public interface UserMapper {
    User getUserByName(String userToken);
    Boolean isValidToken(String userToken);
    void insertUser(User user);
}
