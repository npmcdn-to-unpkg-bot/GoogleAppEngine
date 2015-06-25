package com.appspot.cloudserviceapi.data;

/**
 * http://stackoverflow.com/questions/108822/delete-all-data-for-a-kind-in-google-app-engine
 */
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class DrupalInitServlet extends HttpServlet {

	private long count = 1;
	
	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		request.getSession().setAttribute("dis_count", count);
		response.setContentType("text/plain");

		// queue.add(url("/path?a=b&c=d").method(Method.GET));

		// response.getWriter().println("Drupal 6.2 database initialized!");

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Queue queue = QueueFactory.getDefaultQueue();
		try {
			Transaction txn = ds.beginTransaction();

			response
					.getWriter()
					.println(count++ + 
							": Drupal 6.2 initialization started, processing 47 tables with 942 SQL statements ...");

			queue.add(withUrl("/cibt/scripts/init.jsp").method(Method.GET));

			txn.commit();

		} catch (DatastoreFailureException e) {
			e.printStackTrace();
		}
	}

}