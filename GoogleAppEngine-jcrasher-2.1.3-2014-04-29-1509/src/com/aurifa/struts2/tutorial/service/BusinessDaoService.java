package com.aurifa.struts2.tutorial.service;

import java.util.Arrays;
import java.util.List;

//import com.aurifa.struts2.tutorial.dao.BusinessDao;
//import com.aurifa.struts2.tutorial.dao.BusinessNoDBdao;
import com.appspot.cloudserviceapi.data.Datastore;
import com.ypg.db.dao.BusinesDao;
import com.ypg.db.dao.gae.BusinesDaoImpl;

public class BusinessDaoService implements BusinessService {
    private BusinesDao dao;

    public BusinessDaoService() {
//        this.dao = new BusinessNoDBdao();
    	this.dao = new BusinesDaoImpl(Datastore.getDS());
    }

	public List getAllBusiness() {
        return Arrays.asList(dao.findAll());
	}
}
