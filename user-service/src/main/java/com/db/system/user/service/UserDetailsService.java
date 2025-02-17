package com.db.system.user.service;

import com.db.system.user.data.model.User;

public interface UserDetailsService {
    Boolean isValidToken(String userToken);
    User getUserByName(String userToken);
}
