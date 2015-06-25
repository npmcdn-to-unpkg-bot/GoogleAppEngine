package com.appspot.cloudserviceapi.service.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.appspot.cloudserviceapi.dao.GeniuDao;
import com.appspot.cloudserviceapi.dao.gae.DaoFactoryImpl;
import com.appspot.cloudserviceapi.data.Datastore;
import com.appspot.cloudserviceapi.dto.Geniu;
import com.google.appengine.api.datastore.Transaction;
//import com.spoledge.audao.db.dao.DaoException;

public class GeniusManagerImpl implements GeniusManager {

	private static GeniuDao dao = (new DaoFactoryImpl()).createGeniuDao(Datastore.getDS());
	private List<Geniu> myBeans = getGenius();
		
	public List<Geniu> getGenius() {	//jprofiler - 60 ms invoked 6 times per rendered page (about 10 ms each)
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		Geniu[] g = (Geniu[]) dao.findAll();
		List retVal = new ArrayList(Arrays.asList(g));
        tx.commit();

		return retVal;
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
            tx.commit();
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
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
	                tx.commit();    			
	    		} else {
	                dao.insert(myBean);
	                tx.commit();    			
	    		}
    		}
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
	        throw e;
		}
	}

	public Geniu getGeniu(Long id) {
		Geniu retVal = null;
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		
//		for (Geniu myBean : myBeans) {
//			if (myBean.getId() != null && myBean.getId().equals(id)) {
//				retVal = myBean;
//			}
//		}
		retVal = (Geniu) dao.findByPrimaryKey(id);
        tx.commit();    			
		return retVal;
	}

	@Override
	public String toString() {
		return myBeans.toString();
	}


}