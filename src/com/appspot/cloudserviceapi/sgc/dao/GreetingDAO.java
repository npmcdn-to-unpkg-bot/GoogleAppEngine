package com.appspot.cloudserviceapi.sgc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.cloudserviceapi.data.Persistence;
import com.appspot.cloudserviceapi.sgc.Protect;
import com.appspot.cloudserviceapi.sgc.guestbook.Greeting;

public class GreetingDAO {

	// private static boolean purgeMode = true; //remember to change the id type
	// to String as well
	private boolean purgeMode = false; // the id type has to be Long

	public Greeting get(String primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		Greeting wo = null;

		javax.jdo.Query query = pm.newQuery(Greeting.class, "id == pk");
		try {
			List<Greeting> results = null;
			if (purgeMode) {
				query.declareParameters("String pk");
				results = (List<Greeting>) query.execute(primaryKey);
			} else {
				query.declareParameters("Long pk");
				Long pk = Long.valueOf(primaryKey).longValue();
				results = (List<Greeting>) query.execute(pk);
			}
			if (results.iterator().hasNext()) {
				for (Greeting e : results) {
					System.out.println("greeting from " + e.getNickname() + " found");
					wo = e;
					break;
				}
			} else {
				System.out.println("no greeting");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return wo;
	}

	public List getList(Long primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		Greeting wo = null;
		List a = null;
		List<Greeting> results = null;
		javax.jdo.Query query = null;
		try {
			if (purgeMode) {
				query.declareParameters("String pk");
				results = (List<Greeting>) query.execute(primaryKey);
			} else {
				query.declareParameters("Long pk");
				Long pk = Long.valueOf(primaryKey).longValue();
				results = (List<Greeting>) query.execute(pk);
			}
			if (results.iterator().hasNext()) {
				a = new ArrayList();
				for (Greeting e : results) {
					System.out.println("greeting from " + e.getAuthor());
					a.add(e);
				}
			} else {
				System.out.println("no greeting");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return a;
	}

	public List getList() {
		PersistenceManager pm = Persistence.getManager();
		List<Greeting> results = null;
		javax.jdo.Query query = null;
		try {
			query = pm.newQuery(Greeting.class);
			results = (List<Greeting>) query.execute();
		} finally {
			query.closeAll();
			pm.close();
		}

		return results;
	}

	/**
	 * This does not seem to work, could be a bug related to the GAE4J JDO or
	 * the codes.
	 */
	public List getTransientList() {
		PersistenceManager pm = Persistence.getManager();

		List results = new ArrayList(getList());
		try {
			pm.makeTransientAll(results);
		} finally {
			pm.close();
		}

		return results;
	}

	public List getCloneList() {
		PersistenceManager pm = Persistence.getManager();
		List<Greeting> results = null;
		javax.jdo.Query query = null;
		try {
			query = pm.newQuery(Greeting.class);
			results = (List<Greeting>) query.execute();
		} finally {
			query.closeAll();
		}

		List<Greeting> woList = results; // getList();
		List<Greeting> clonedList = new ArrayList<Greeting>(woList.size());
		try {
			for (Greeting wo : woList) {
				clonedList.add((Greeting) wo.clone());
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		pm.close();
		return clonedList;
	}

	public void save(Greeting wo) {
		// XXX GAE4J JDO limitation? id can not be zero
		/*
		 * if(wo.getId() == 0) { //wo.setId(new Long(1));
		 * System.err.println("Id can not be zero!"); return; }
		 */
		PersistenceManager pm = Persistence.getManager();
		try {
			pm.makePersistent(wo);
		} finally {
			pm.close();
		}
	}

	public boolean remove(Long primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		boolean retVal = false;

		javax.jdo.Query query = pm.newQuery(Greeting.class, "id == pk");
		if (purgeMode) {
			query.declareParameters("String pk");
		} else {
			query.declareParameters("Long pk");
		}
		try {
			List<Greeting> results = (List<Greeting>) query.execute(primaryKey);
			String fName = null;
			if (results.iterator().hasNext()) {
				for (Greeting e : results) {
					fName = e.getNickname();
					pm.deletePersistent(e);
					System.out.println("greeting from " + fName + " removed ");
				}
			} else {
				System.out.println("no greeting found for removal");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return retVal;
	}

	/**
	 * Use this with caution. It will remove all Greeting instances from the
	 * datastore!!!
	 */
	public boolean purgeGreeting() {
		boolean retVal = false;

		PersistenceManager pm = Persistence.getManager();
		if (Protect.isAdmin()) { // extra caution
			// Query
			Query query = pm.newQuery(Greeting.class);
			List<Greeting> results = (List<Greeting>) query.executeWithArray();
			// results = (List<Greeting>) pm.detachCopyAll(results);
			Iterator it = results.iterator();
			Greeting wo = null;
			while (it.hasNext()) {
				wo = (Greeting) it.next();
				// Delete
				try {
					pm.deletePersistent(wo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			retVal = true;
		}
		pm.close();

		return retVal;
	}
}
