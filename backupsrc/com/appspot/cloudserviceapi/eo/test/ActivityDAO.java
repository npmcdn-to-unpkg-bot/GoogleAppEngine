package com.appspot.cloudserviceapi.eo.test;

//import tapp.model.eo.*;
//import com.appspot.cloudserviceapi.eo.data.*;

import java.util.List;

//import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for CdActivity
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ActivityDAO /*extends HibernateDaoSupport*/ {

	protected void initDao() {
		// do nothing
	}

	public void save(Activity transientInstance) {
		System.out.println("saving CdActivity instance");
		try {
//			getHibernateTemplate().save(transientInstance);
			System.out.println("save successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Activity persistentInstance) {
		System.out.println("deleting CdActivity instance");
		try {
//			getHibernateTemplate().delete(persistentInstance);
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Activity findById(java.lang.Long id) {
		System.out.println("getting CdActivity instance with id: " + id);
		try {
//			Activity instance = (Activity) getHibernateTemplate().get(
//					Activity.class.getName(), id);
			return null;	//instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Activity> findByExample(Activity instance) {
		System.out.println("finding CdActivity instance by example");
		try {
//			List<Activity> results = (List<Activity>) getHibernateTemplate()
//					.findByExample(instance);
//			System.out.println("find by example successful, result size: " + results.size());
			return null;	//results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		System.out.println("finding CdActivity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CdActivity as model where model."
					+ propertyName + "= ?";
			return null;	//getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			throw re;
		}
	}

/*	public List<CdActivity> findByProfileId(Object profileId) {
		return findByProperty(PROFILE_ID, profileId);
	}

	public List<CdActivity> findByCdActivityType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<CdActivity> findByCdActivityName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List<CdActivity> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<CdActivity> findByPasswordSalt(Object passwordSalt) {
		return findByProperty(PASSWORD_SALT, passwordSalt);
	}

	public List<CdActivity> findByCdHashMethodId(Object cdHashMethodId) {
		return findByProperty(CD_HASH_METHOD_ID, cdHashMethodId);
	}

	public List<CdActivity> findByCdStatusId(Object cdStatusId) {
		return findByProperty(CD_STATUS_ID, cdStatusId);
	}

	public List<CdActivity> findByCdSecurityQuestionId(Object cdSecurityQuestionId) {
		return findByProperty(CD_SECURITY_QUESTION_ID, cdSecurityQuestionId);
	}

	public List<CdActivity> findBySecurityQuestionAnswer(Object securityQuestionAnswer) {
		return findByProperty(SECURITY_QUESTION_ANSWER, securityQuestionAnswer);
	}

	public List<CdActivity> findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List<CdActivity> findByAddedBy(Object addedBy) {
		return findByProperty(ADDED_BY, addedBy);
	}

	public List<CdActivity> findByUpdatedBy(Object updatedBy) {
		return findByProperty(UPDATED_BY, updatedBy);
	}
*/
	public List findAll() {
		System.out.println("finding all CdActivity instances");
		try {
			String queryString = "from CdActivity";
			return null;	//getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Activity merge(Activity detachedInstance) {
		System.out.println("merging CdActivity instance");
		try {
//			Activity result = (Activity) getHibernateTemplate().merge(detachedInstance);
			System.out.println("merge successful");
			return null;	//result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Activity instance) {
		System.out.println("attaching dirty CdActivity instance");
		try {
//			getHibernateTemplate().saveOrUpdate(instance);
			System.out.println("attach successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Activity instance) {
		System.out.println("attaching clean CdActivity instance");
		try {
//			getHibernateTemplate().lock(instance, LockMode.NONE);
			System.out.println("attach successful");
		} catch (RuntimeException re) {
			System.out.println("attach failed: " + re);
			throw re;
		}
	}

	public static ActivityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ActivityDAO) ctx.getBean("CdActivityDAO");
	}
}