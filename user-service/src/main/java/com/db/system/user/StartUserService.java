package com.db.system.user;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;

public class StartUserService {
    public static void main(String[] args) throws Exception {
        Server server = newServer(8085);
        try {
            server.start();
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
