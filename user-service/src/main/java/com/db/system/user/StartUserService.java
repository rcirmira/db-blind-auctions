package com.db.system.user;

import com.db.system.user.config.database.DatabaseConfig;
import com.db.system.user.config.database.MyBatisConfig;
import com.db.system.user.config.hk2.JerseyConfig;
import com.db.system.user.dao.UserMapper;
import com.db.system.user.data.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class StartUserService {
    private static final Logger LOG = LoggerFactory.getLogger(StartUserService.class);

    public static void main(String[] args) throws Exception {
        int port = 8085;
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
            String users = Files.readString(Paths.get(Objects.requireNonNull(DatabaseConfig.class.getClassLoader().getResource("users.txt")).toURI()));
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
        } catch (URISyntaxException | IOException e) {
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
