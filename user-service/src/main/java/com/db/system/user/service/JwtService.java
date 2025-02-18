package com.db.system.user.service;

import com.db.system.data.model.user.User;
import com.db.system.user.service.internal.model.TokenMappingPair;

public interface JwtService {
    String getToken(User user);
    TokenMappingPair getTokenMappingPair(String token);
}
