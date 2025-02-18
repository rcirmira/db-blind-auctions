package com.db.system.auction.config.database;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseConfig {
    private static SqlSessionFactory sqlSessionFactory;

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

            String schemaSql = Files.readString(Paths.get(Objects.requireNonNull(DatabaseConfig.class.getClassLoader().getResource("schema.sql")).toURI()));

            statement.execute(schemaSql);
        } catch (URISyntaxException | IOException | SQLException e) {
            throw new RuntimeException("Failed to initialize database schema", e);
        }
    }
}
