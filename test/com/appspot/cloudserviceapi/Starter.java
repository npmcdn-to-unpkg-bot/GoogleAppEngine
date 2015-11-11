package com.appspot.cloudserviceapi;

import java.util.EnumSet;

import io.swagger.config.ScannerFactory;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.DefaultJaxrsScanner;
import io.swagger.sample.util.ApiOriginFilter;
import io.swagger.sample.util.Bootstrap_;
import io.swagger.servlet.config.DefaultServletConfig;
import io.swagger.servlet.listing.ApiDeclarationServlet;

import javax.servlet.DispatcherType;
import javax.ws.rs.core.Application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.StdErrLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.SessionScope;

import app.controller.CrudService;

/**
 * https://github.com/swagger-api/swagger-ui/issues/711 
 * https://github.com/swagger-api/swagger-core/issues/1539
 * http://stackoverflow.com/questions/25815294/add-swagger-servlet-support-to-project-with-swagger-and-jaxrs
 * @setup:
 * 1. Make sure you add jetty jar files FIRST i.e. bin/jetty/*.jar
 * 2. Folowed by all swagger jar files SECOND i.e. bin/swagger/*.jar
 * 3. Project src/jar files LAST!
 * @launch:
 * 1. Run Starter
 * 2. Visit http://127.0.0.1:8002/api-docs
 */
public class Starter {
//  private static final Logger log = Logger.getLogger(Starter.class);
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

	public static void main(String[] args) throws Exception {
        System.out.println( "Initializing Swagger BeanConfig" );
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setDescription("This is a app.");
        beanConfig.setTitle("Swagger Petstore");
        beanConfig.setHost("127.0.0.1:8002/");
        beanConfig.setBasePath("/api-docs");
        beanConfig.setFilterClass("io.swagger.sample.util.ApiAuthorizationFilterImpl");
        beanConfig.setResourcePackage("io.swagger.sample.resource");
        beanConfig.setContact("apiteam@swagger.io");
        beanConfig.setLicense("Apache 2.0");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");

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
	    
		// Create servlet context
		final ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContext.setContextPath("/");
		servletContext.addServlet(CrudService.class, "/ws/*");
		servletContext.addFilter(ApiOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
		
		// Setup Swagger handlers
//        servletContext.addServlet(new ServletHolder(), "");
//		servletContext.addServlet(DefaultServletConfig.class, "/");
//	    servletContext.setInitParameter("swagger.resource.package", "io.swagger.sample.servlet");
//	    servletContext.setInitParameter("swagger.resource.package", "app.controller");
//	    servletContext.setInitParameter("swagger.api.basepath", "http://127.0.0.1:8002");
//	    servletContext.setInitParameter("api.version", "1.0.0");
		servletContext.addServlet(ApiDeclarationServlet.class, "/api-docs/*");
	    servletContext.setInitParameter("jersey.config.server.wadl.disableWadl", "true");
//	    servletContext.setInitParameter("javax.ws.rs.Application", MyServices.class.getName());
	    servletContext.setInitParameter("javax.ws.rs.Application", Application.class.getName());
//	    servletContext.setInitParameter("javax.ws.rs.Application", CrudService.class.getName());
//        ServletContextHandler restContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        restContextHandler.setContextPath("/ws/crud");
//        restContextHandler.addServlet(h, "/*");
//		servletContext.addServlet(Bootstrap_.class, "");
//        serve("").with(Bootstrap.class);

        
		final HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { 
//				restContextHandler,	// REST handler???
				webResourceHandler, // Web handler
				servletContext, // Servlet handler
				new DefaultHandler() }); // Default handler returns 404 (NOT
											// FOUND) for anything else        

		server.setHandler(handlers);
		server.start();
		server.join();
	}
}
