package com.appspot.cloudserviceapi.sci.fios;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * Servlet implementation class CalendarEventServletII
 */
public class CalendarEventServletII extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarEventServletII() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String temp = null;
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		try {
			//user.corp2.fios.corp21234
			System.out.println(request.getParameter("time"));
			DateFormat dfGoogle = new SimpleDateFormat("yyyy-MM-dd'T HH:mm:ss'");
			String strDate = "2010/5/13 17:10:50";// request.getParameter("time");////
			System.out.println(strDate.substring(10, 18));
			String str1 = strDate.substring(10, 18);
			Date date = new Date(strDate);
			// Object obj = "2010/5/13 17:10:50";//request.getParameter("time");
			System.out.println(dfGoogle.format(date).toString());
			String strFormat = dfGoogle.format(date).toString();
			System.out.println(strFormat.substring(0, 10));
			String str2 = strFormat.substring(0, 10);

			String googleFormatDt = str2 + "T" + str1;
			System.out.println("googleFormatDt---" + googleFormatDt);

			String sessionToken = "CKrlvOuiFhCwmIaI-P____8BGKCmpcUD";
			CalendarService myService = new CalendarService("corp2-calEvent-1");
			myService.setAuthSubToken(sessionToken, null);

			System.out.println("Content type------------"
					+ myService.getContentType());

			// URL feedUrl = new
			// URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
			// URL feedUrl = new
			// URL("http://www.google.com/calendar/feeds/user.corp2.fios@gmail.com/private/full");
			URL feedUrl = new URL(
					"http://www.google.com/calendar/feeds/default/private/full");

			// Send the request and receive the response:
			CalendarEventFeed myFeed = myService.getFeed(feedUrl,
					CalendarEventFeed.class);

			System.out.println("after get feed----"
					+ myFeed.getEntries().size());
			if (myFeed.getEntries().size() > 0) {
				for (int i = 0; i < myFeed.getEntries().size(); i++) {
					CalendarEventEntry entry = (CalendarEventEntry) myFeed
							.getEntries().get(i);

					String myEntryTitle = entry.getTitle().getPlainText();
					DateTime dt = entry.getUpdated();
					System.out.println(dt.toString());
					System.out.println(entry.getReminder());

					// System.out.println("myEntryTitle----"+myEntryTitle);
					System.out.println("\t" + myEntryTitle);
					temp = DateFormat.getDateTimeInstance(
							DateFormat.SHORT, DateFormat.SHORT).format(
									new Date(dt.getValue()))
									+ " " + myEntryTitle + "\n";
					sb.append(temp);
				}
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		response.setContentLength(sb.length());
		out.println(sb.toString());
	}
}
