package com.appspot.cloudserviceapi.sci.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityManager;

import tapp.model.ServiceRegistry;

import com.appspot.cloudserviceapi.common.Constants;
import com.appspot.cloudserviceapi.data.EMF;
import com.appspot.cloudserviceapi.data.Persistence;
import com.appspot.cloudserviceapi.sgc.Protect;
//import com.appspot.cloudserviceapi.util.CacheController;
//import com.appspot.cloudserviceapi.util.CacheKey;

/**
 * This class makes use of both JPA and JDO.
 *
 */
public class ServiceRegistryDAO {

	private boolean purgeMode = false; // the id type has to be String under purge mode
//	private static List<ServiceRegistry> clonedList;	//used by UI, acts as cache as CacheController is not working
	private static final String ALL_SR_LIST = "AllServiceRegistryList";
	
	public ServiceRegistry findServiceRegistryByService(String service) {
		EntityManager em = EMF.get().createEntityManager();
		ServiceRegistry gud;
		try {
			javax.persistence.Query q = (javax.persistence.Query) em.createQuery("select u from tapp.model.ServiceRegistry u where u.service = ?1");
			q.setParameter(1, service);
			gud = (ServiceRegistry) q.getSingleResult();
			//TODO commented out for now due to https://community.jboss.org/message/868254#868254
//		} catch (com.google.apphosting.api.ApiProxy.OverQuotaException e1) {
//			throw e1;
		} catch (Exception e) {
			//e.printStackTrace();
			gud = null;
		} finally {
			em.close();
		}
		return gud;
	}
	
	public ServiceRegistry get(long primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		ServiceRegistry wo = null;

		javax.jdo.Query query = pm.newQuery(ServiceRegistry.class, "id == pk");
		try {
			List<ServiceRegistry> results = null;
			if (purgeMode) {
				query.declareParameters("String pk");
				results = (List<ServiceRegistry>) query.execute(primaryKey);
			} else {
				query.declareParameters("Long pk");
				Long pk = Long.valueOf(primaryKey).longValue();
				results = (List<ServiceRegistry>) query.execute(pk);
			}
			if (results.iterator().hasNext()) {
				for (ServiceRegistry e : results) {
					System.out.println("ServiceRegistry " + e.getId());
					wo = e;
					break;
				}
			} else {
				System.out.println("no ServiceRegistry");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return wo;
	}

	public List getList(long primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		ServiceRegistry wo = null;
		List a = null;
		List<ServiceRegistry> results = null;
		javax.jdo.Query query = null;
		try {
			if (purgeMode) {
				query.declareParameters("String pk");
				results = (List<ServiceRegistry>) query.execute(primaryKey);
			} else {
				query.declareParameters("Long pk");
				Long pk = Long.valueOf(primaryKey).longValue();
				results = (List<ServiceRegistry>) query.execute(pk);
			}
			if (results.iterator().hasNext()) {
				a = new ArrayList();
				for (ServiceRegistry e : results) {
					System.out.println("ServiceRegistry " + e.getId());
					a.add(e);
				}
			} else {
				System.out.println("no ServiceRegistry");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return a;
	}

	public List getList() {
		PersistenceManager pm = Persistence.getManager();
		List<ServiceRegistry> results = null;
		javax.jdo.Query query = null;
		try {
			query = pm.newQuery(ServiceRegistry.class);
			results = (List<ServiceRegistry>) query.execute();
		} finally {
			query.closeAll();
			pm.close();
		}

		return results;
	}

	/**
	 * This does not seem to work, could be a bug related to the 4J JDO or
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

	//TBD - this could be the performance bottleneck hotspot! the size should be saved as a variable in a row/property instead
	public List getCloneList() {
		//List<ServiceRegistry> clonedList = (List<ServiceRegistry>) CacheController.get(ALL_SR_LIST);
		List<ServiceRegistry> clonedList = null;
//		if(clonedList == null || clonedList.size() == 0) {	//cut time from 1 ms to 4 micro s for every SR page rendered (in grid) or saved!
			PersistenceManager pm = Persistence.getManager();
			List<ServiceRegistry> results = null;
			javax.jdo.Query query = null;
			try {
				query = pm.newQuery(ServiceRegistry.class);
				results = (List<ServiceRegistry>) query.execute();
			} finally {
				query.closeAll();
			}

			List<ServiceRegistry> woList = results; // getList();
			clonedList = new ArrayList<ServiceRegistry>(woList.size());
			try {
				for (ServiceRegistry wo : woList) {
					clonedList.add((ServiceRegistry) wo.clone());
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			//CacheController.put(ALL_SR_LIST, clonedList);
			pm.close();
//		}
		return clonedList;
	}

	public static void clearCache() {
		//KISS approach
		//List<ServiceRegistry> clonedList = (List<ServiceRegistry>) CacheController.get(ALL_SR_LIST);
//		if(clonedList != null) {
//			clonedList = null;
//			//CacheController.put(ALL_SR_LIST, clonedList);
//		}
//		System.out.println("ServiceRegistryDAO.java: clearCache() cache cleared!!!");
	}

	public void save(ServiceRegistry wo) {
		// XXX 4J JDO limitation? id can not be zero
		/*
		 * if(wo.getId() == 0) { //wo.setId(new Long(1));
		 * System.err.println("Id can not be zero!"); return; }
		 */
		PersistenceManager pm = Persistence.getManager();
		try {
			pm.makePersistent(wo);
			//List<ServiceRegistry> clonedList = (List<ServiceRegistry>) CacheController.get(ALL_SR_LIST);
			//update the cache too
//			boolean found = false;
//			if(clonedList != null) {
//				Iterator<ServiceRegistry> itr = clonedList.iterator();
//				ServiceRegistry sr = null;
//				while(itr.hasNext()) {
//					sr = (ServiceRegistry)itr.next();
//					if(sr.getId().equals(wo.getId())) {
//						itr.remove();
//						clonedList.add(wo);
//						found = true;
//						break;
//					}
//				}
//				if(!found) {
//					clonedList.add(wo);
//				}
//			}
			//CacheController.put(ALL_SR_LIST, clonedList);
		} finally {
			pm.close();
		}
	}

	public boolean remove(Long primaryKey) {
		PersistenceManager pm = Persistence.getManager();
		boolean retVal = false;

		javax.jdo.Query query = pm.newQuery(ServiceRegistry.class, "id == pk");
		if (purgeMode) {
			query.declareParameters("String pk");
		} else {
			query.declareParameters("Long pk");
		}
		try {
			List<ServiceRegistry> results = (List<ServiceRegistry>) query.execute(primaryKey);
			long id = -1L;
			if (results.iterator().hasNext()) {
				String targetService = null;
				for (ServiceRegistry e : results) {
					id = e.getId();
					targetService = e.getService();
					pm.deletePersistent(e);
					//List<ServiceRegistry> clonedList = (List<ServiceRegistry>) CacheController.get(ALL_SR_LIST);
					//=== remove the cache too
//					Iterator<ServiceRegistry> itr = clonedList.iterator();
//					ServiceRegistry sr = null;
//					while(itr.hasNext()) {
//						sr = (ServiceRegistry)itr.next();
//						System.out.println("sr [" + sr.getService() + "] targetService [" + targetService + "]");
//						if(sr.getService() != null && sr.getService().equals(targetService)) {
//							clonedList.remove(sr);
//							break;
//						}
//					}
					//CacheController.put(ALL_SR_LIST, clonedList);
					System.out.println("ServiceRegistry " + id + " removed ");
				}
			} else {
				System.out.println("no ServiceRegistry found for removal");
			}
		} finally {
			query.closeAll();
			pm.close();
		}

		return retVal;
	}

	/**
	 * Use this with caution. It will remove all ServiceRegistry instances from the
	 * datastore!!!
	 */
	public boolean purge() {
		boolean retVal = false;

		PersistenceManager pm = Persistence.getManager();
		if (Protect.isAdmin()) { // extra caution
			// Query
			Query query = pm.newQuery(ServiceRegistry.class);
			List<ServiceRegistry> results = (List<ServiceRegistry>) query.executeWithArray();
			// results = (List<ServiceRegistry>) pm.detachCopyAll(results);
			Iterator it = results.iterator();
			ServiceRegistry wo = null;
			while (it.hasNext()) {
				wo = (ServiceRegistry) it.next();
				// Delete
				try {
					pm.deletePersistent(wo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			retVal = true;
		} else {
			throw new RuntimeException(Constants.NO_RIGHT_TO + "purge services!");
		}
		pm.close();

		return retVal;
	}
	
	public Long exist(ServiceRegistry service) {
		PersistenceManager pm = Persistence.getManager();

		Long retVal = -1L;

		Query q = pm.newQuery(ServiceRegistry.class, "service == :service");
		q.setUnique(true);
		
		ServiceRegistry attachedUser = (ServiceRegistry) q.execute(service.getService());
		
		if(attachedUser != null) {
			retVal = attachedUser.getId();
		}
		
		pm.close();
		
		return retVal;
	}

}