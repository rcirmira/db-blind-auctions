package com.db.system.user.service;

import com.db.system.user.data.model.User;
import jakarta.inject.Singleton;

@Singleton
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public boolean isValidToken(String userToken) {
        return true;
    }

    @Override
    public User getUser(String userToken) {
        User user = new User();

        user.setId(1);
        user.setDescription("fake user");
        user.setToken(userToken);
        user.setName("fake name");

        return user;
    }
}
