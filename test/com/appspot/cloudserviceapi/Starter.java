package com.appspot.cloudserviceapi;

//import org.apache.cxf.transport.servlet.CXFServlet;
import io.swagger.sample.util.ApiOriginFilter;
import io.swagger.servlet.config.DefaultServletConfig;
import io.swagger.servlet.listing.ApiDeclarationServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.log.StdErrLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.controller.CrudService;

/**
 * https://github.com/swagger-api/swagger-ui/issues/711 
 * http://stackoverflow.com/questions/25815294/add-swagger-servlet-support-to-project-with-swagger-and-jaxrs
 * @setup:
 * 1. Make sure you add jetty jar files FIRST i.e. bin/jetty/*.jar
 * 2. Folowed by all swagger jar files SECOND i.e. bin/swagger/*.jar
 * 3. Project src/jar files LAST!
 */
public class Starter {
//  private static final Logger log = Logger.getLogger(Starter.class);
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

	public static void main(String[] args) throws Exception {
//		BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.2");
//        beanConfig.setBasePath("http://localhost:8002/api");
//        beanConfig.setResourcePackage("io.swagger.resources");
//        beanConfig.setScan(true);
        
		StdErrLog lg = new StdErrLog();
		lg.setDebugEnabled(true);
		org.eclipse.jetty.util.log.Log.setLog(lg);
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(8002);
		server.addConnector(connector);

		// Setup web handler
		final ResourceHandler webResourceHandler = new ResourceHandler();
		webResourceHandler.setDirectoriesListed(false);
		webResourceHandler.setWelcomeFiles(new String[] { "index.html" });
//		webResourceHandler.setResourceBase("webapp");
	    
		// Create servlet context
		final ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		servletContext.addEventListener(new DefaultResteasyBootstrap());
		servletContext.setContextPath("/");
		servletContext.addServlet(CrudService.class, "/ws/crud");
//		servletContext.setWar("src/main/webapp");
//		servletContext.addFilter(MyFilter.class, "/", 1);
		servletContext.addFilter(ApiOriginFilter.class, "/", null);
		
//		servletContext.addServlet(DefaultServlet.class, "/");
		// Setup Swagger handlers
		servletContext.addServlet(CrudService.class, "/ws/crud/*");

		servletContext.addServlet(DefaultServletConfig.class, "/");
	    servletContext.setInitParameter("swagger.resource.package", "io.swagger.sample.servlet");
	    servletContext.setInitParameter("swagger.api.basepath", "http://localhost:8002");
	    servletContext.setInitParameter("api.version", "1.0.0");
		servletContext.addServlet(ApiDeclarationServlet.class, "/api/*");
	    
	    
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
