package com.db.system.user.service;

import com.db.system.user.data.model.User;

public interface UserDetailsService {
    boolean isValidToken(String userToken);
    User getUser(String userToken);
}
