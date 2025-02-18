package com.db.system.backend;

import com.db.system.backend.config.hk2.JerseyConfig;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartBackendService {
    private static final Logger LOG = LoggerFactory.getLogger(StartBackendService.class);

    public static void main(String[] args) throws Exception {
        int port = 8085;
        LOG.info("Starting Jetty server...");
        Server server = newServer(port);
        try {
            server.start();
            LOG.info("Started Jetty server on http://localhost:{}", port);
            LOG.info("Started Swagger UI on http://localhost:{}/swagger-ui", port);

            server.join();
        } finally {
            server.destroy();
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
