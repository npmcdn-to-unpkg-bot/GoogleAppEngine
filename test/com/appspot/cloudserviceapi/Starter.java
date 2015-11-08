package com.appspot.cloudserviceapi;

//import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import app.controller.CrudService;

/**
 * http://stackoverflow.com/questions/25815294/add-swagger-servlet-support-to-project-with-swagger-and-jaxrs
 * @setup:
 * 1. Make sure you add jetty jar files FIRST i.e. bin/jetty/*.jar
 * 2. Folowed by all swagger jar files SECOND i.e. bin/swagger/*.jar
 * 3. Project src/jar files LAST!
 */
public class Starter {

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(8002);
		server.addConnector(connector);

		// Setup web handler
		final ResourceHandler webResourceHandler = new ResourceHandler();
		webResourceHandler.setDirectoriesListed(false);
		webResourceHandler.setWelcomeFiles(new String[] { "index.html" });
		webResourceHandler.setResourceBase("webapp");

		// Create servlet context
		final ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		servletContext.addEventListener(new DefaultResteasyBootstrap());
		servletContext.setContextPath("/");
		servletContext.addServlet(CrudService.class, "/ws/crud");

		// Setup Swagger handlers
//		servletContext.addServlet(new ServletHolder(new SwaggerJaxrsConfig()), "");

		// Add MyService Servlet
//		final ServletHolder servletHolder = new ServletHolder(new HttpServletDispatcher());
//		servletHolder.setInitParameter("javax.ws.rs.Application", MyServiceResource.class.getName());
//		servletContext.addServlet(servletHolder, "/*");

		// Add Admin Servlet
//		final ServletHolder adminServletHolder = new ServletHolder(new HttpServletDispatcher());
//		servletContext.addServlet(adminServletHolder, "/admin/*");

		// Add Admin Servlets
//		servletContext.addServlet(new ServletHolder(new PingServlet()), "/ping");
//		servletContext.addServlet(new ServletHolder(new ThreadDumpServlet()), "/threadDump");

		final HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { webResourceHandler, // Web handler
				servletContext, // Servlet handler
				new DefaultHandler() }); // Default handler returns 404 (NOT
											// FOUND) for anything else

		server.setHandler(handlers);
		server.start();
		server.join();
	}
}
