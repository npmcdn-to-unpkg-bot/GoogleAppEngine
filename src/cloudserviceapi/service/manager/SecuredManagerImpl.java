package cloudserviceapi.service.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.appspot.cloudserviceapi.dao.SecureDao;
import com.appspot.cloudserviceapi.dao.gae.DaoFactoryImpl;
import com.appspot.cloudserviceapi.data.Datastore;
import com.appspot.cloudserviceapi.dto.Secure;
import com.google.appengine.api.datastore.Transaction;
//import com.spoledge.audao.db.dao.DaoException;

public class SecuredManagerImpl implements SecuredManager {

	private static SecureDao dao = (new DaoFactoryImpl()).createSecureDao(Datastore.getDS());
	private List<Secure> myBeans = getSecured();
		
	public List<Secure> getSecured() {
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		Secure[] g = (Secure[]) dao.findAll();
		List retVal = new ArrayList(Arrays.asList(g));
        tx.commit();

		return retVal;
	}

	public void setSecure(List<Secure> myBeans) {
		this.myBeans = myBeans;
	}

	public SecureDao getDao() {
		return dao;
	}

	public void setDao(SecureDao dao) {
		SecuredManagerImpl.dao = dao;
	}

	public void delete(Long id) {
        Transaction tx = null;
		try {
            tx = Datastore.getDS().beginTransaction();
			dao.deleteByPrimaryKey(id);
			myBeans = getSecured();
            tx.commit();
		} catch (Exception e) {
	        tx.rollback();
			e.printStackTrace();
		}
	}

	public void save(Secure myBean) throws Exception {
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
    			Secure g = getSecure(myBean.getId());
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

	public Secure getSecure(Long id) {
		Secure retVal = null;
        Transaction tx = null;
        tx = Datastore.getDS().beginTransaction();
		
//		for (Secure myBean : myBeans) {
//			if (myBean.getId() != null && myBean.getId().equals(id)) {
//				retVal = myBean;
//			}
//		}
		retVal = (Secure) dao.findByPrimaryKey(id);
        tx.commit();    			
		return retVal;
	}

	@Override
	public String toString() {
		return myBeans.toString();
	}


}