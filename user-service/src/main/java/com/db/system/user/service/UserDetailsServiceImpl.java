package com.db.system.user.service;

import com.db.system.user.config.database.MyBatisConfig;
import com.db.system.user.dao.UserMapper;
import com.db.system.user.data.model.User;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;

@Singleton
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public Boolean isValidToken(String userToken) {
        // TODO implement
        return false;
    }

    @Override
    public User getUserByName(String name) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserByName(name);
        }
    }
}
