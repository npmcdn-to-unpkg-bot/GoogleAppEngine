package app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.common.Constants;
import app.common.SecurityUtils;
import app.model.Movie;
import app.model.User;
import app.model.UserEndpoint;

import com.appspot.cloudserviceapi.common.JsonUtil;

/**
 * http://www.kendoui.com/blogs/teamblog/posts/13-03-05/
 * handling_crud_with_the_kendo_ui_jsp_wrappers.aspx
 */
@SuppressWarnings("serial")
public class CrudService extends HttpServlet {
	// === KISS: assume only one handler per object!
	private static List<CrudServiceCallback> objectHandlers = new ArrayList<CrudServiceCallback>();
	
	public static void addObjectHandlers(CrudServiceCallback handler) {
		objectHandlers.add(handler);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		doPost(request, response);
		System.out.println("CrudService: doGet invoked");
		handleServiceRequest(request, response);
	}

	private String getValue(HttpServletRequest req, String reqName) {
		String retVal = "";

		if(req.getParameter(reqName) != null) {
			retVal = req.getParameter(reqName);
		}

		return retVal;
	}

	/**
	 * Main method to call any supporting domain objects like a Movie by invoking their corresponding parseRequest(). See #1.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Object handleRequest(HttpServletRequest request) throws Exception {
		Object item = null;

		// VALIDATION SHOULD BE DONE HERE AS WELL

		CrudServiceCallback h = null;
		Iterator<CrudServiceCallback> it = objectHandlers.iterator();
		String uid = getValue(request, Constants.UNIVERSAL_ID);
		System.out.println("CrudService:handleRequest() uid = [" + uid + "] received");
		while (it.hasNext()) {
			h = (CrudServiceCallback) it.next();
			item = h.parseRequest(request);	//**** #1 here !!! ****
			if (item != null) {
				// get the type of operation
				String action = getValue(request, "action");
				System.out.println("action = '" + action + "'");
				
				if (action.equals("create")) {
					// create the item
					try {
						item = JsonUtil.toString(h.doCreateItem(item));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				if (action.equals("update")) {
					// add the item id onto the model and update the item
					item = JsonUtil.toString(h.doUpdateItem(item));
				} 
				else
				if (action.equals(Constants.ACT_DELETE)) {
					// delete the item
					item = JsonUtil.toString(h.doDeleteItem(item));
				}				
				else
				if (action.equals(Constants.MIGRATE_DELETE)) {
					String username = getValue(request, "uid");
					String id = getValue(request, "id");
					String oid = getValue(request, "oid");
					System.out.println("CrudService: MIGRATE delete: username = [" + username + "]");
					((MovieHandler)h).setUid(username);
					((MovieHandler)h).setOid(oid);
					item = JsonUtil.toString(h.doMigratePurgeItem(username, id));
					System.out.println("crud list migrate service done");
				}
				else
				if (action.equals(Constants.SCHEDULED)) {
					item = h.doGetAllItems();
					System.out.println("crud list scheduled service");
				}
				else
				if (action.equals(Constants.SHARED)) {
					item = h.doGetAllItems();
					System.out.println("crud list shared service");
				}
				else
				if (action.equals(Constants.MIGRATE)) {
					String username = getValue(request, "uid");
					System.out.println("CrudService: MIGRATE update: username = [" + username + "]");
					((MovieHandler)h).setUid(username);
					item = JsonUtil.toString(h.doMigrateAllItems(username));
					System.out.println("crud list migrate service done");
				}
				/** begin THIS IS JUST A TEST !!! */
				else
				if (action.equals("purgecalendar")) {
					uid = (String)getValue(request, "uid");
					User u = UserHandler.getUser(uid);
					List<Movie> l = null;
					if(u != null) {
						l = u.getMovie();
					}
					MovieHandler.purgeScheduledMovies(uid, l);
					item = JsonUtil.toString(l);
					
					System.out.println("calendar events purged for user [" + uid + "] !");
				}
//				else
//				if(action != null && action.equals(Constants.ACT_UPGRADE))
//				{
//					System.out.println("upgrade: activated");
//					String uid = (String)getValue(request, "uid");
//					List<Movie> allList = null;
//						MovieHandler handler = new MovieHandler();
//						allList = handler.getMovies();
//						if(allList != null && allList.size() > 0) {
//							System.out.println("upgrade: scanning movie size " + allList.size() );
//							User user = UserHandler.getUser(uid);
//							List<Movie> newList = handler.migrateOwnedMovies(user, allList);
//							if(newList != null && newList.size() > 0) {
//								if(user != null) {
//									user.setMovie(newList);
//									handler.saveParent(user);
//									System.out.println("upgrade: movie collection upgraded for user [" + uid + "] = " + allList.size());
//								} else {
//									System.out.println("upgrade: user [" + uid + "] not found!");
//								}
//							} else {
//								System.out.println("upgrade: zero size collection for user [" + uid + "], nothing to migrate");
//							}
//						} else {
//							System.out.println("upgrade: zero movies in total collections for all users, nothing to migrate");
//						}
//					item = JsonUtil.toString(allList);
//				}
				/** end THIS IS JUST A TEST !!! */
				else {
					long itemId = getValue(request, "id") == "" ? 0 : Long.parseLong(getValue(request, "id"));
					if(itemId > 0) {
						// get the item
						item = JsonUtil.toString(h.doGetItem(itemId));
						System.out.println("crud get service");
					} else
					if(itemId == 0 && (action == null || action.equals(""))) {
						// list the items
						String temp = h.doGetItems();
						item = temp;
						System.out.println("crud list service, response size " + temp.length());
					}
				}
				break; // assuming only one handler per object
			}
		}

		return item;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("CrudService: doPost invoked");
		handleServiceRequest(request, response);
	}

	private void handleServiceRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			SecurityUtils.handleOWASPSession(request, response);	//OWASP
			//response.setHeader("Cache-Control","no-cache");
			// Set to expire far in the past.
			response.setHeader("Expires", "-1");
			
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");
			//=== set the content type we are sending back as JSON
			//response.setContentType("application/json");
			//=== supports any language (UNICODE) / i18n stuff with two lines of codes that specify "utf-8"
			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/json; charset=UTF-8");	//text/json type avoided: "Unexpected end of input" during jQuery's json.parse() on Chrome/Webkit browsers unfortunately
			response.setContentType("text/plain; charset=UTF-8");	//Note: caused SyntaxError: "Unexpected end of input" during jQuery's json.parse() on Chrome/Webkit browsers
			//response.setContentType("text/cache-manifest");
			//http://stackoverflow.com/questions/49547/making-sure-a-web-page-is-not-cached-across-all-browsers
			//request.getSession().invalidate();


			// parse the rest of the request into an employee object
			Object item = handleRequest(request);

			response.getWriter().print(item);
		} catch (Exception e) {
			//e.printStackTrace();
			response.setContentType("text/plain");
			response.sendError(response.SC_FORBIDDEN, e.getMessage());
		}
	}
}
