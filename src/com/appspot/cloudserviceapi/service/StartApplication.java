package com.appspot.cloudserviceapi.service;

//import org.apache.wicket.protocol.http.WicketServlet;
//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.bio.SocketConnector;
//import org.eclipse.jetty.servlet.ServletHolder;
//import org.eclipse.jetty.webapp.WebAppContext;

//import wicket.WicketApplication;


/**
 * Sources:
 * 
 * http://www.codecommit.com/blog/java/so-long-wtp-embedded-jetty-for-me.
 * https://cwiki.apache.org/WICKET/jetty6-testing.html
 * 
 */
public class StartApplication {
    public static void main(String[] args) throws Exception {
/*        Server server = new Server();
        SocketConnector conn = new SocketConnector();
        conn.setPort(8080);
        server.setConnectors(new Connector[]{conn});

        ServletHolder servletHolder = new ServletHolder(new WicketServlet());
        servletHolder.setInitParameter("applicationClassName", WicketApplication.class.getName());
        servletHolder.setInitOrder(1);
         
        WebAppContext wah = new WebAppContext();
        
        wah.setContextPath("/xmm");
        //wah.setWar("target/xmm.war"); 	//uncomment this if you'd like to test the war instead
        wah.setWar("war/WEB-INF/classes");		//comment this out if you use the war
        server.setHandler(wah);
        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);  
        
        server.start();
        server.join();
 */   }
}