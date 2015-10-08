package com.aurifa.struts2.tutorial.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aurifa.struts2.tutorial.model.Business;

public class BusinessNoDBdao implements BusinessDao {
    private static List businesses;
    private static Map businessesMap;
    static {
        businesses = new ArrayList();
        businesses.add(new Business( new Integer(100), "Accounting" ));
        businesses.add(new Business( new Integer(200), "R & D"));
        businesses.add(new Business( new Integer(300), "Sales" ));
        businessesMap = new HashMap();
        Iterator iter = businesses.iterator();
        while( iter.hasNext() ) {
            Business dept = (Business)iter.next();
            businessesMap.put(dept.getBusinessId(), dept );
        }

     }
    public List getAllBusiness() {
        return businesses;
    }
    public Map getBusinessMap() {
        return businessesMap;
    }
}
