package com.aurifa.struts2.tutorial.dao;

import java.util.List;
import java.util.Map;

import com.aurifa.struts2.tutorial.model.Business;

public interface BusinessDao {
    public List getAllBusiness();
    public Map getBusinessMap();
}