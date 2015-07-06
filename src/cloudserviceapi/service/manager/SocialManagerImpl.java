package cloudserviceapi.service.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.appspot.cloudserviceapi.dao.HumaDao;
import com.appspot.cloudserviceapi.dao.gae.DaoFactoryImpl;
import com.appspot.cloudserviceapi.data.Datastore;
import com.appspot.cloudserviceapi.dto.Huma;
import com.google.appengine.api.datastore.Transaction;
//import com.spoledge.audao.db.dao.DaoException;

public class SocialManagerImpl implements SocialManager {

	private static HumaDao dao = (new DaoFactoryImpl()).createHumaDao(Datastore.getDS());
	private List<Huma> myBeans = getSocial();
		
	public List<Huma> getSocial() {
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		Huma[] g = (Huma[]) dao.findAll();
		List retVal = new ArrayList(Arrays.asList(g));
        tx.commit();

		return retVal;
	}

	public void setHuma(List<Huma> myBeans) {
		this.myBeans = myBeans;
	}

	public HumaDao getDao() {
		return dao;
	}

	public void setDao(HumaDao dao) {
		SocialManagerImpl.dao = dao;
	}

	public void delete(Long id) {
        Transaction tx = null;
		try {
            tx = Datastore.getDS().beginTransaction();
			dao.deleteByPrimaryKey(id);
			myBeans = getSocial();
            tx.commit();
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
		}
	}

	public void save(Huma myBean) throws Exception {
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
    			Huma g = getHuma(myBean.getId());
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

	public Huma getHuma(Long id) {
		Huma retVal = null;
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		
//		for (Huma myBean : myBeans) {
//			if (myBean.getId() != null && myBean.getId().equals(id)) {
//				retVal = myBean;
//			}
//		}
		retVal = (Huma) dao.findByPrimaryKey(id);
        tx.commit();    			
		return retVal;
	}

	@Override
	public String toString() {
		return myBeans.toString();
	}


}