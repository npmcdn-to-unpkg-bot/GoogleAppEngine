package com.appspot.cloudserviceapi.sci.fios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * Servlet implementation class CalendarEventServletI
 */
public class CalendarEventServletI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarEventServletI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			doPost(request,response);
/*			String singleUseToken = request.getParameter("token");
		    if (singleUseToken == null) {
		      /*AuthSub.RequestUri requestUri = new AuthSub.RequestUri();
		      requestUri.next = request.getRequestURL().toString();
		      requestUri.scope = "http://picasaweb.google.com/data";
		      requestUri.session = 1;
		      response.sendRedirect(requestUri.build());
		    	System.out.println("in if");
		    	String nextUrl = "http://localhost:8080/CalendarWebProject/CalendarEventServletI";
				String scope = "http://www.google.com/calendar/feeds/";
				boolean secure = false;  // set secure=true to request secure AuthSub tokens
				boolean session = true;
				String authSubUrl = AuthSubUtil.getRequestUrl(nextUrl, scope, secure, session);
				System.out.println("authSubUrl-----"+authSubUrl);
				response.sendRedirect(authSubUrl);
		    } else {
		    	System.out.println("in else");
		      GoogleTransport transport = new GoogleTransport("google-sample-1.0");
		      AuthSub.SessionTokenResponse resp= AuthSub.exchangeForSessionToken(singleUseToken);
		      resp.setAuthorizationHeader(transport);
		      System.out.println("content type ---"+response.getContentType());
		      System.out.println("token is------------"+resp.token);
		*/      
		      //ServletOutputStream outStream = response.getOutputStream();
		      //System.out.println(outStream);
		      
		      
		  //  	System.out.println("Single use token is --- "+singleUseToken);
		//		String sessionToken = AuthSubUtil.exchangeForSessionToken(URLDecoder.decode(singleUseToken,"UTF-8"), null);

				
			//	System.out.println("sessionToken---------------------"+sessionToken);
		      
		      
		      // Calendar service code
		//      String sessionToken = "CKrlvOuiFhCwmIaI-P____8BGKCmpcUD";
		//      CalendarService myService = new CalendarService("corp2-calEvent-1");
		//      myService.setAuthSubToken(sessionToken,null);
		/*      
		      System.out.println("Content type------------"+myService.getContentType());
		    	  //System.out.println("after service instantiation---"+myService.getAuthToken("user.corp2.fios", "corp21234", "", "", "cl", "corp2-calEvent-1"));
		    	 // myService.setUserCredentials("user.corp2.fios","corp21234",ClientLoginAccountType.GOOGLE);
		    	  
		    	  //URL feedUrl = new URL("http://www.google.com/calendar/feeds/user.corp2.fios@gmail.com/private/full");
		      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		    	  System.out.println("after user credentials");
		    	 System.out.println(myService.getVersion());
		    	 
		    	  // Send the request and receive the response:
		    	  CalendarEventFeed myFeed = myService.getFeed(feedUrl, CalendarEventFeed.class);*/
		//      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");

		//      CalendarQuery myQuery = new CalendarQuery(feedUrl);
		//      myQuery.setMinimumStartTime(DateTime.parseDateTime("2006-03-16T00:00:00"));
		//      myQuery.setMaximumStartTime(DateTime.parseDateTime("2006-03-24T23:59:59"));
		      
		 //     CalendarEventFeed resultFeed =  myService.query(myQuery, CalendarEventFeed.class);
		  //    System.out.println("result feed is----"+resultFeed);
		//      System.out.println(resultFeed.getCanPost());
		      
		    	
		      // System.out.println("after get feed----"+myFeed.getEntries().size());
		    	/*  if(myFeed.getEntries().size()>0){
		    		  for(int i=0; i<myFeed.getEntries().size(); i++) {
		    			  CalendarEventEntry entry = (CalendarEventEntry)
		        	      myFeed.getEntries().get(i); 
		    			  
		    			  String myEntryTitle = entry.getTitle().getPlainText();
		    	    	  //System.out.println("myEntryTitle----"+myEntryTitle);
		    	          System.out.println("\t" + myEntryTitle);
		    	        }
		    }*/
		  
		
		      /*
		      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		      System.out.println("after url");
		      //GDataRequest gReq = myService.createEntryRequest(feedUrl);
		      System.out.println("after create entry req");
		     // gReq.execute();
		     System.out.println(myService.handlesCookies());
		      myService.setHandlesCookies(false);
		      CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
		      System.out.println("Your calendars:");
		      System.out.println();
		      for (int i = 0; i < resultFeed.getEntries().size(); i++) {
		        CalendarEntry entry = resultFeed.getEntries().get(i);
		        System.out.println("\t" + entry.getTitle().getPlainText());
		      }*/
	    
	//	}
	//	catch(AuthenticationException e) {
	//        e.printStackTrace();
	 //     }
	//	
	//      catch(MalformedURLException e) {
	//        e.printStackTrace();
	//      }
	//      catch(ServiceException e) {
	//        e.printStackTrace();
	//      }
	//      catch(IOException e) {
	//        e.printStackTrace();
	//      } 
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			 
		      // Calendar service code
		      String sessionToken = "CKrlvOuiFhCwmIaI-P____8BGKCmpcUD";
		      CalendarService myService = new CalendarService("corp2-calEvent-1");
		      myService.setAuthSubToken(sessionToken,null);
		      
		      System.out.println("Content type------------"+myService.getContentType());
		    	  //System.out.println("after service instantiation---"+myService.getAuthToken("user.corp2.fios", "corp21234", "", "", "cl", "corp2-calEvent-1"));
		    	 // myService.setUserCredentials("user.corp2.fios","corp21234",ClientLoginAccountType.GOOGLE);
		    	  
		    	  //URL feedUrl = new URL("http://www.google.com/calendar/feeds/user.corp2.fios@gmail.com/private/full");
		      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		    	  System.out.println("after user credentials");
		    	 System.out.println(myService.getVersion());
		    	 
		    	  // Send the request and receive the response:
		    	  CalendarEventFeed myFeed = myService.getFeed(feedUrl, CalendarEventFeed.class);
	/*	      System.out.println("before url");
		      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");

		      CalendarQuery myQuery = new CalendarQuery(feedUrl);
		      myQuery.setMinimumStartTime(DateTime.parseDateTime("2006-03-16T00:00:00"));
		      myQuery.setMaximumStartTime(DateTime.parseDateTime("2006-03-24T23:59:59"));
		      
		      CalendarEventFeed resultFeed =  myService.query(myQuery, CalendarEventFeed.class);
		      System.out.println("result feed is----"+resultFeed);
		      System.out.println(resultFeed.getEntryPostLink());*/
		      
		    	
		       System.out.println("after get feed----"+myFeed.getEntries().size());
		    	  if(myFeed.getEntries().size()>0){
		    		  for(int i=0; i<myFeed.getEntries().size(); i++) {
		    			  CalendarEventEntry entry = (CalendarEventEntry)
		        	      myFeed.getEntries().get(i); 
		    			  
		    			  String myEntryTitle = entry.getTitle().getPlainText();
		    	    	  //System.out.println("myEntryTitle----"+myEntryTitle);
		    	          System.out.println("\t" + myEntryTitle);
		    	        }
		    }
		  
		
		      /*
		      URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		      System.out.println("after url");
		      //GDataRequest gReq = myService.createEntryRequest(feedUrl);
		      System.out.println("after create entry req");
		     // gReq.execute();
		     System.out.println(myService.handlesCookies());
		      myService.setHandlesCookies(false);
		      CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
		      System.out.println("Your calendars:");
		      System.out.println();
		      for (int i = 0; i < resultFeed.getEntries().size(); i++) {
		        CalendarEntry entry = resultFeed.getEntries().get(i);
		        System.out.println("\t" + entry.getTitle().getPlainText());
		      }*/
	    
		}
		catch(AuthenticationException e) {
	        e.printStackTrace();
	      }
		
	      catch(MalformedURLException e) {
	        e.printStackTrace();
	      }
	      catch(ServiceException e) {
	        e.printStackTrace();
	      }
	      catch(IOException e) {
	        e.printStackTrace();
	      
		}
	}

}
