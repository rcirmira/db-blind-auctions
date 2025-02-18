package com.db.system.auction.config.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisConfig {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // Load MyBatis configuration
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

            // Build the SqlSessionFactory using the environment that references the data source
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development");
        } catch (IOException e) {
            throw new RuntimeException("Error initializing MyBatis", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
