package pe.edu.utp.utils;

import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.session.SessionHandler;

public class JettyUTP {

    private Server server;
    private int port;
    private String static_path;
    private ServletContextHandler context;

    public JettyUTP(int port, String static_path) {
        this.port = port;
        this.static_path = static_path;
        this.server = new Server(port);
        ServerConnector connector = new ServerConnector(this.server);
        this.server.addConnector(connector);
        this.context = new ServletContextHandler(ServletContextHandler.SESSIONS); // Use the sessions constant
        this.context.setContextPath("/");

        // Agregar DefaultServlet para atender contenido estático
        ServletHolder servletHolderStatic = context.addServlet(DefaultServlet.class, "/");
        servletHolderStatic.setInitParameter("resourceBase", static_path);
        servletHolderStatic.setAsyncSupported(true);

        // Establecer el SessionHandler en el ServletContextHandler automáticamente
        this.context.setHandler(new SessionHandler());
    }

    public ServletHolder addServlet(Class<? extends Servlet> servlet, String path) {
        return context.addServlet(servlet, path);
    }

    public void start() throws Exception {
        // Link the context to the server.
        this.server.setHandler(this.context);
        this.server.start(); // Iniciar el servidor
    }
}
