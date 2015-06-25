package com.aurifa.struts2.tutorial.service;

import java.util.Arrays;
import java.util.List;

import com.appspot.cloudserviceapi.data.Datastore;
import com.spoledge.audao.db.dao.DaoException;
import com.ypg.db.dao.PartnerDao;
import com.ypg.db.dao.gae.PartnerDaoImpl;
import com.ypg.db.dto.Partner;

public class PartnerDaoService implements PartnerService {
	private PartnerDao dao;

	public PartnerDaoService() {
		// this.dao = new PartnerNoDBdao();
		this.dao = new PartnerDaoImpl(Datastore.getDS());
	}

	public List getAllPartners() {
		return Arrays.asList(dao.findAll());
	}

	public void updatePartner(Partner emp) throws DaoException {
			dao.update(emp.getId(), emp);
	}

	public void deletePartner(long id) throws DaoException {
			dao.deleteByPrimaryKey(id);
	}

	public Partner getPartner(long id) {
		return dao.findByPrimaryKey(id);
	}

	public void insertPartner(Partner emp) throws DaoException {
			dao.insert(emp);
	}
}
