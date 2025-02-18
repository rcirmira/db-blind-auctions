package com.db.system.user.service;

import com.db.system.data.model.user.User;

public interface UserDetailsService {
    User getUserByToken(String userToken);
    String getUserToken(String userToken);
}
