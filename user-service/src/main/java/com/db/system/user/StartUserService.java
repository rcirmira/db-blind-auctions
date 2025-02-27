package com.db.system.user;

import com.db.system.user.config.database.DatabaseConfig;
import com.db.system.user.config.database.MyBatisConfig;
import com.db.system.user.config.hk2.JerseyConfig;
import com.db.system.user.dao.UserMapper;
import com.db.system.data.model.user.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StartUserService {
    private static final Logger LOG = LoggerFactory.getLogger(StartUserService.class);

    public static void main(String[] args) throws Exception {
        int port = 8086;
        LOG.info("Starting Jetty server...");
        Server server = newServer(port);
        try {
            initializeDatabase();

            server.start();
            LOG.info("Started Jetty server on http://localhost:{}", port);

            server.join();
        } finally {
            server.destroy();
        }
    }

    private static void initializeDatabase() {
        LOG.info("Initialize the database");

        // Initialize MyBatis and Database
        DatabaseConfig.getDataSource();
        SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();

        try {
            String users;
            try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("users.txt")) {
                if (inputStream == null) {
                    throw new IllegalStateException("schema.sql not found in JAR root!");
                }

                users = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
            LOG.info("Obtained users to initialize: {}", users);
            Scanner scanner = new Scanner(users);
            scanner.useDelimiter(",");
            while(scanner.hasNext()) {
                try (SqlSession session = sqlSessionFactory.openSession(true)) {
                    String name = scanner.next();
                    LOG.info("Initializing auction user with name: {}", name);
                    UserMapper mapper = session.getMapper(UserMapper.class);
                    mapper.insertUser(new User(name));
                }
            }
        } catch (IOException e) {
            LOG.warn("Cannot initialize auction users in the DB", e);
        }
    }

    public static Server newServer(int port)
    {
        // Use Jersey config
        JerseyConfig jerseyConfig = new JerseyConfig();

        // Create Jersey servlet
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(jerseyConfig));

        // Start Jetty
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");

        // Attach context to server and start
        server.setHandler(context);

        return server;
    }

}
