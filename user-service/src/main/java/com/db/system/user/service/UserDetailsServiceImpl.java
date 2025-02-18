package com.db.system.user.service;

import com.db.system.user.dao.UserDao;
import com.db.system.data.model.user.User;
import com.db.system.user.service.internal.model.TokenMappingPair;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;
    private final JwtService jwtService;

    @Inject
    public UserDetailsServiceImpl(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    @Override
    public User getUserByToken(String userToken) {
        TokenMappingPair pair = jwtService.getTokenMappingPair(userToken);
        if(pair != null) {
            User user = userDao.getUserByName(pair.getName());
            if (user != null && user.getId().equals(pair.getId()) && user.getName().equals(pair.getName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String getUserToken(String name) {
        User user = userDao.getUserByName(name);
        if(user != null) {
            return jwtService.getToken(user);
        }
        return  null;

    }
}
