package com.appspot.cloudserviceapi.eo.test;

//import tapp.model.eo.*;
//import com.appspot.cloudserviceapi.eo.data.*;

import java.util.List;

//import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for College
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CollegeDAO /*extends HibernateDaoSupport*/ {

	protected void initDao() {
		// do nothing
	}

	public void save(College transientInstance) {
		System.out.println("saving College instance");
		try {
//			getHibernateTemplate().save(transientInstance);
			System.out.println("save successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(College persistentInstance) {
		System.out.println("deleting College instance");
		try {
//			getHibernateTemplate().delete(persistentInstance);
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public College findById(java.lang.Long id) {
		System.out.println("getting College instance with id: " + id);
		try {
//			College instance = (College) getHibernateTemplate().get(
//					College.class.getName(), id);
			return null;	//instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<College> findByExample(College instance) {
		System.out.println("finding College instance by example");
		try {
//			List<College> results = (List<College>) getHibernateTemplate()
//					.findByExample(instance);
//			System.out.println("find by example successful, result size: " + results.size());
			return null;	//results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		System.out.println("finding College instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from College as model where model."
					+ propertyName + "= ?";
			return null;	//getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			throw re;
		}
	}

/*	public List<College> findByProfileId(Object profileId) {
		return findByProperty(PROFILE_ID, profileId);
	}

	public List<College> findByCollegeType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<College> findByCollegeName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List<College> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<College> findByPasswordSalt(Object passwordSalt) {
		return findByProperty(PASSWORD_SALT, passwordSalt);
	}

	public List<College> findByCdHashMethodId(Object cdHashMethodId) {
		return findByProperty(CD_HASH_METHOD_ID, cdHashMethodId);
	}

	public List<College> findByCdStatusId(Object cdStatusId) {
		return findByProperty(CD_STATUS_ID, cdStatusId);
	}

	public List<College> findByCdSecurityQuestionId(Object cdSecurityQuestionId) {
		return findByProperty(CD_SECURITY_QUESTION_ID, cdSecurityQuestionId);
	}

	public List<College> findBySecurityQuestionAnswer(Object securityQuestionAnswer) {
		return findByProperty(SECURITY_QUESTION_ANSWER, securityQuestionAnswer);
	}

	public List<College> findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List<College> findByAddedBy(Object addedBy) {
		return findByProperty(ADDED_BY, addedBy);
	}

	public List<College> findByUpdatedBy(Object updatedBy) {
		return findByProperty(UPDATED_BY, updatedBy);
	}
*/
	public List findAll() {
		System.out.println("finding all College instances");
		try {
			String queryString = "from College";
			return null;	//getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public College merge(College detachedInstance) {
		System.out.println("merging College instance");
		try {
//			College result = (College) getHibernateTemplate().merge(detachedInstance);
			System.out.println("merge successful");
			return null;	//result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(College instance) {
		System.out.println("attaching dirty College instance");
		try {
//			getHibernateTemplate().saveOrUpdate(instance);
			System.out.println("attach successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(College instance) {
		System.out.println("attaching clean College instance");
		try {
//			getHibernateTemplate().lock(instance, LockMode.NONE);
			System.out.println("attach successful");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public static CollegeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CollegeDAO) ctx.getBean("CollegeDAO");
	}
}