package com.aurifa.struts2.tutorial.service;

import java.util.List;

import com.spoledge.audao.db.dao.DaoException;
import com.ypg.db.dto.Partner;
//import com.aurifa.struts2.tutorial.model.Partner;

public interface PartnerService {
    public List getAllPartners();
    public void updatePartner(Partner partner) throws DaoException;
    public void deletePartner(long id) throws DaoException;
    public Partner getPartner(long id);
    public void insertPartner(Partner partner) throws DaoException;
}
