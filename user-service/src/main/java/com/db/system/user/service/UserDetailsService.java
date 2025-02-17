package com.db.system.user.service;

import com.db.system.user.data.model.User;

public interface UserDetailsService {
    User getUserByToken(String userToken);
    String getUserToken(String userToken);
}
