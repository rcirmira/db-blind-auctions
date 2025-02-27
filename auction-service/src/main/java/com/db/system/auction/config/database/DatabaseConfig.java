package com.db.system.auction.config.database;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    public static DataSource getDataSource() {
        // TODO get the config data from mybatis-config.xml
        PooledDataSource dataSource = new PooledDataSource(
                "org.h2.Driver",
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "sa",
                ""
        );

        initializeDatabase(dataSource);
        return dataSource;
    }

    private static void initializeDatabase(PooledDataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            String schemaSql;
            try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("schema.sql")) {
                if (inputStream == null) {
                    throw new IllegalStateException("schema.sql not found in JAR root!");
                }

                schemaSql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
            LOG.info("Obtained SQL schema: {}", schemaSql);

            statement.execute(schemaSql);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to initialize database schema", e);
        }
    }
}
