package com.appspot.cloudserviceapi.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

public class MyLocalEntityManagerFactoryBean //extends LocalEntityManagerFactoryBean 
{
	private static EntityManagerFactory mInstance;

//	@Override
//	protected EntityManagerFactory createNativeEntityManagerFactory()
//			throws PersistenceException {
//		if (mInstance == null)
//			mInstance = super.createNativeEntityManagerFactory();
//		return mInstance;
//	}

	public MyLocalEntityManagerFactoryBean() {
		super();
	}
}