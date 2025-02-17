package com.db.system.user.service;

import com.db.system.user.data.model.User;
import com.db.system.user.service.internal.model.TokenMappingPair;

public interface JwtService {
    String getToken(User user);
    TokenMappingPair getTokenMappingPair(String token);
}
