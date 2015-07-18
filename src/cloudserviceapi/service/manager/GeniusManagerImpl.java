package cloudserviceapi.service.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tapp.model.ServiceRegistry;

import com.appspot.cloudserviceapi.dao.GeniuDao;
import com.appspot.cloudserviceapi.dao.gae.DaoFactoryImpl;
import com.appspot.cloudserviceapi.data.Datastore;
import com.appspot.cloudserviceapi.dto.Geniu;
import com.google.appengine.api.datastore.Transaction;
//import com.spoledge.audao.db.dao.DaoException;

public class GeniusManagerImpl implements GeniusManager {

	private static List<Geniu> clonedList;	//used by UI to save datastore read free quota
	private static GeniuDao dao = (new DaoFactoryImpl()).createGeniuDao(Datastore.getDS());
	private List<Geniu> myBeans = getGenius();
		
	public List<Geniu> getGenius() {	//jprofiler - 60 ms invoked 6 times per rendered page (about 10 ms each)
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
        Geniu[] g = null;
		if(clonedList == null) {
			g = (Geniu[]) dao.findAll();
			Geniu[] woList = g;
			clonedList = new ArrayList<Geniu>(woList.length);
			try {
				for (Geniu wo : woList) {
					clonedList.add((Geniu) wo.clone());
				}
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
        tx.commit();

		return clonedList;
	}

	public void setGeniu(List<Geniu> myBeans) {
		this.myBeans = myBeans;
	}

	public GeniuDao getDao() {
		return dao;
	}

	public void setDao(GeniuDao dao) {
		GeniusManagerImpl.dao = dao;
	}

	public void delete(Long id) {
        Transaction tx = null;
		try {
            tx = Datastore.getDS().beginTransaction();
			dao.deleteByPrimaryKey(id);
			myBeans = getGenius();
			//=== remove the cache too
			removeCache(id);
            tx.commit();
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
		}
	}

	private void removeCache(Long id) {
		Geniu sr = null;
		for(int i = 0; i < clonedList.size(); i++) {
			sr = (Geniu)clonedList.get(i);
			if(sr.getId() != null && sr.getId() == id) {
				clonedList.remove(i);
				break;
			}
		}
	}

	public void save(Geniu myBean) throws Exception {
		Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		if (myBeans.indexOf(myBean) > -1) {
			// update
			myBeans.set(myBeans.indexOf(myBean), myBean);
		} else {
			myBeans.add(myBean);
		}
		try {
    		if (myBean != null) {
    			Geniu g = getGeniu(myBean.getId());
    			if(g != null) {
	    			dao.update(g.getId(), myBean);
	    			updateCache(g.getId(), myBean);
	                tx.commit();    			
	    		} else {
	                dao.insert(myBean);
	                insertCache(myBean);
	                tx.commit();    			
	    		}
    		}
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
	        throw e;
		}
	}

	public void insertCache(Geniu wo) {
		if(clonedList != null) clonedList.add(wo);
	}
	
	public void updateCache(Long long1, Geniu wo) {
		boolean found = false;
		if(clonedList != null) {
			Iterator<Geniu> itr = clonedList.iterator();
			Geniu sr = null;
			for(int i = 0; i < clonedList.size(); i++) {
				sr = (Geniu)clonedList.get(i);
				if(sr.getId() == long1) {
					clonedList.set(i, wo);
					break;
				}
			}
		}
	}

	public Geniu getGeniu(Long id) {
		Geniu retVal = null;
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		retVal = (Geniu) dao.findByPrimaryKey(id);
        tx.commit();    			
		return retVal;
	}

	@Override
	public String toString() {
		return myBeans.toString();
	}

	public void updateCache(Geniu wo) {
		boolean found = false;
		List<Geniu> clonedList = getGenius();
		if(clonedList  != null) {
			Iterator<Geniu> itr = clonedList.iterator();
			Geniu sr = null;
			for(int i = 0; i < clonedList.size(); i++) {
				sr = (Geniu)clonedList.get(i);
				if(sr.getId() == wo.getId()) {
					clonedList.set(i, wo);
					found = true;
					break;
				}
			}
			if(!found) clonedList.add(wo);
		}
	}

	/** Warning: This will clear all entries in the cache and will cause a query for all the entries again from the datastore 
	 * and it is EXPENSIVE (could consume around 10% of the datastore read!!!!) */
	public static void clearCache() {
		if(clonedList != null) {
			clonedList = null;
		}
		System.out.println("GeniusManagerImpl.java: clearCache() cache cleared!!!");
	}

}