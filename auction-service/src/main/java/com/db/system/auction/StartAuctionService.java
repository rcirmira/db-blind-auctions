package com.db.system.auction;

import com.db.system.auction.config.database.DatabaseConfig;
import com.db.system.auction.config.database.MyBatisConfig;
import com.db.system.auction.config.hk2.JerseyConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartAuctionService {
    private static final Logger LOG = LoggerFactory.getLogger(StartAuctionService.class);

    public static void main(String[] args) throws Exception {
        int port = 8087;
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
