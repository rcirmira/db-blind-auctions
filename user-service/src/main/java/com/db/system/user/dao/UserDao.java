package com.db.system.user.dao;

import com.db.system.user.config.database.MyBatisConfig;
import com.db.system.user.data.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDao {
    private final SqlSessionFactory sqlSessionFactory;

    public UserDao() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    public void insertUser(User user) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(user);
        }
    }
}
