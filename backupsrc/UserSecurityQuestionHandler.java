package cloudserviceapi.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import passwordchange.core.CommonUtil;
import passwordchange.core.UserSecurityQuestion;
import app.model.User;

import com.appspot.cloudserviceapi.common.JsonUtil;
import com.appspot.cloudserviceapi.data.EMF;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Format:
 * 
 * GET LIST OF ITEMS:
 * http://localhost:8888/ws/crud?type=passwordchange.core.UserSecurityQuestion&uid=j
 * GET ITEM:
 * http://localhost:8888/ws/crud?type=passwordchange.core.UserSecurityQuestion&id=1
 * DELETE ITEM:
 * http://localhost:8888/ws/crud?type=passwordchange.core.UserSecurityQuestion&id=1&action=delete
 * CREATE ITEM:
 * http://localhost:8888/ws/crud?type=passwordchange.core.UserSecurityQuestion&id=1&action=create
 * id=1
 * name=name 1
 * description=text 1
 * UPDATE ITEM:
 * http://localhost:8888/ws/crud?type=passwordchange.core.UserSecurityQuestion&id=1&action=update
 * id=1
 * name=name 2
 * description=text 2
 *
 */
public class UserSecurityQuestionHandler implements CrudServiceCallback, ServletContextListener {
	public static String DEFAULT_HQ_TIME = "US/Eastern";
	private String uid;	//the parent id
	private Long movieId;
	private MovieHandler movieHandler = new MovieHandler();

	static {
//		dao = new UserSecurityQuestionEndpoint();
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Object parseRequest(HttpServletRequest request) {
		UserSecurityQuestion item = null;
		uid = CommonUtil.getValue(request, "uid");
		String temp1 = CommonUtil.getValue(request, "movieid");
		if(temp1 == null || temp1.isEmpty()) {
			movieId = -1L;
		} else {
			movieId = new Long(temp1);
		}
		
		String type = CommonUtil.getValue(request, "type");

		if(type != null && type.equals("passwordchange.core.UserSecurityQuestion")) {
			String temp = CommonUtil.getValue(request, "id");
			if(temp != null) {
				item.setUaName(temp);	//this is only good for update/delete
				// parse the rest of the information off of the request
			}
		}
		
		return item;
	}

	private User getParent() throws Exception {
//		User retVal = null;
//		User u = new User();
//		UserHandler uh = new UserHandler();
//		u.setName(uid);
//		u = uh.getUserByName(u);
//		if(u.getKey() != null) {
//			retVal = u;
//		} else {
//			System.out.println("no parent with uid [" + uid + "] found, nothing will be done for object instance of " + u.getClass().getName());
//		}
//		return retVal;
		return CommonHandler.getParent(uid);
	}

	private void saveParent(User item) throws Exception {
		UserHandler uh = new UserHandler();
		uh.doUpdateItem(item);
	}
	
	/**
	 * Get the associated UserSecurityQuestion object(s) which is part of a recurring event.
	 * @param user
	 * @return
	 */
	public long deleteItemsByPattern(String pattern) {
		long retVal = 0;
		EntityManager em = getEntityManager();
		try {
			System.out.println("deleting UserSecurityQuestion by pattern [" + pattern + "] ...");
		    String queryStr = String.format("delete from " + UserSecurityQuestion.class.getName() + " c" +
		    								" where c.recurringPattern = :pattern");
            javax.persistence.Query query = em.createQuery(queryStr);
            query.setParameter("pattern", pattern);
            retVal = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return retVal;
	}

	public Object doCreateItem(Object item) throws Exception {
		String id = null;
		
		UserSecurityQuestion cal = (UserSecurityQuestion)item;
		try {
			User u = getParent();
			if(u != null) {
				List<UserSecurityQuestion> cl = u.getSecurityQuestion();
				if(cl != null) {
					cl.add(cal);
				} else {
					cl = new ArrayList<UserSecurityQuestion>();
					cl.add(cal);
				}
				saveParent(u);
//				id = cal.getKey().getId();
				cal.setUaName(id);	//set the "surrogate key" - important as everything else is depending on this key not the real key.id!
				System.out.println(this.getClass().getName() + " UserSecurityQuestion object inserted with id '" + id + "' q1[" + cal.getQuestion1() + "] q2[" + cal.getQuestion2() + " q3[" + cal.getQuestion3() + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cal;
	}

	public String doGetItems() throws Exception {
		CollectionResponse<UserSecurityQuestion> cr = null;
		Collection<UserSecurityQuestion> l;
		String retVal = "";
		
		try {
			User u = getParent();
			if(u != null) {
				l = u.getSecurityQuestion();
				if(l != null) {		//JPA2
				Iterator<UserSecurityQuestion> it = l.iterator();
				UserSecurityQuestion cal = null;
				while(it.hasNext()) {
					cal = it.next();
//					cal.setId(cal.getKey().getId());	//GAEJ specific
					retVal += JsonUtil.toString(cal);
					retVal = retVal.replaceAll("\\n", "");
				}
				}
				retVal = retVal.replaceAll("\\}\\{", "},{");
				retVal = retVal.replaceAll("all_day", "allDay");	//fullUserSecurityQuestion 1.4 requires it to be "allDay"		
				retVal = "[" + retVal + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

	public Object doGetItem(String itemId) throws Exception {
		Object retVal = null;
		
		EntityManager em = getEntityManager();
		try {
			System.out.println("getting UserSecurityQuestion by id '" + itemId + "' ...");
			User u = getParent();
			//GAEJ specific
			Key parentKey = KeyFactory.createKey(User.class.getSimpleName(), u.getKey().getId());
			Key key = KeyFactory.createKey(parentKey, UserSecurityQuestion.class.getSimpleName(), itemId);
			retVal = em.find(UserSecurityQuestion.class, key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return retVal;
	}
	
	//http://stackoverflow.com/questions/4998819/google-appengine-gae-complete-object-key
	public Object doUpdateItem(Object item) throws Exception {
		EntityManager mgr = getEntityManager();
        EntityTransaction tx = mgr.getTransaction();
        
		User u = getParent();
		UserSecurityQuestion newCal = (UserSecurityQuestion)item;
		UserSecurityQuestion cal = null;
		//GAEJ specific
		Key parentKey = KeyFactory.createKey(User.class.getSimpleName(), u.getKey().getId());
		Key key = KeyFactory.createKey(parentKey, UserSecurityQuestion.class.getSimpleName(), newCal.getUaName());
//		Query q = (Query) getEntityManager().createQuery("select from " + UserSecurityQuestion.class.getSimpleName() + " where id = :f"); 
//		q.setParameter("f", key.getId());
//		cal = (UserSecurityQuestion) q.getSingleResult();
		cal = mgr.find(UserSecurityQuestion.class, key);
//		cal.setTitle(newCal.getTitle());
//		cal.setDescription(newCal.getDescription());
//		cal.setStart(newCal.getStart());
//		cal.setEnd(newCal.getEnd());
//		cal.setAllDay(newCal.getAllDay());
//		cal.setUrl(newCal.getUrl());
        tx.begin();
		mgr.persist(cal);
        tx.commit();
        
//		Long id = cal.getKey().getId();
//		cal.setId(id);	//set the "surrogate key" - important as everything else is depending on this key not the real key.id!
		if(cal != null) {
			System.out.println(cal.getClass().getName() + " object updated with id '" + cal.getUaName() + "' q1[" + cal.getQuestion1() + "] q2[" + cal.getQuestion2() + " q3[" + cal.getQuestion3() + "]");
		}
		
		return cal;
	}
	
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CrudService.addObjectHandlers(this);
		System.out.println(this.getClass().getName() + " added as crud service handler");
	}

	@Override
	public Object doDeleteItem(long itemId) throws Exception {
		Long id = -1L;
		EntityManager mgr = getEntityManager();
        EntityTransaction tx = mgr.getTransaction();	
        
		User u = getParent();
		UserSecurityQuestion cal = null, retVal = new UserSecurityQuestion();
		//GAEJ specific
		Key parentKey = KeyFactory.createKey(User.class.getSimpleName(), u.getKey().getId());
		Key key = KeyFactory.createKey(parentKey, UserSecurityQuestion.class.getSimpleName(), itemId);
		cal = mgr.find(UserSecurityQuestion.class, key);
        if(cal != null) {
//			id = cal.getKey().getId();		//GAEJ specific
//			retVal.setId(id);	//set the "surrogate key" - important as everything else is depending on this key not the real key.id!
			UserSecurityQuestion dummy = new UserSecurityQuestion();
//			dummy.setStartDate(null);
	        tx.begin();
			mgr.remove(cal);
	        tx.commit();
		
			System.out.println("2: " + cal.getClass().getName() + " object deleted with id '" + cal.getUaName() + "' q1[" + cal.getQuestion1() + "] q2[" + cal.getQuestion2() + " q3[" + cal.getQuestion3() + "]");
		}
		
		return id;	
	}
	
	@Override
	public Object doDeleteItem(Object item) throws Exception {
		String id = null;
		if(item != null) {
			UserSecurityQuestion cal = (UserSecurityQuestion)item;
			String tempId = cal.getUaName();
			UserSecurityQuestion cOld = (UserSecurityQuestion)doGetItem(tempId); 
			if(tempId != null) {
				doDeleteItem(id);
				id = tempId;
			}
		}
		
		return id;	
	}

	@Override
	public Object doGetItem(long itemId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doGetAllItems() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doMigrateAllItems() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doMigrateAllItems(String uid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doMigratePurgeItem(String uid, String keyString)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}