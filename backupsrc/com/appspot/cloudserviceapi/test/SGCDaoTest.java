package com.appspot.cloudserviceapi.test;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.appspot.cloudserviceapi.data.PMF;
import com.appspot.cloudserviceapi.data.Persistence;
import com.appspot.cloudserviceapi.sgc.dao.WorkOrderDAO;
import tapp.model.sgc.WorkOrder;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
//import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
//import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * This is based on the guide written at http://code.google.com/appengine/docs/java/tools/localunittesting.html.
 * 
 */
public class SGCDaoTest {

//    private final LocalServiceTestHelper helper =
//        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private PersistenceManager pm = Persistence.getManager();

    @Before
    public void setUp() {
//        helper.setUp();
    }

    @After
    public void tearDown() {
//        helper.tearDown();
    }

    // run this test twice to prove we're not leaking any state across tests
    private void doTest() {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery pq = null;
        int count = -1;
        pq = ds.prepare(new Query("yam"));
        count = pq.countEntities();
        assertEquals(0, count);
        ds.put(new Entity("yam"));
        ds.put(new Entity("yam"));
        pq = ds.prepare(new Query("yam"));
        count = pq.countEntities();
        assertEquals(2, count);
    }

    @Test
    public void testGAEDatastoreInsert1() {
        doTest();
    }

    @Test
    public void testGAEDatastoreInsert2() {
        doTest();
    }
    
    @Test
	public void testWorkOrderSave() {
		WorkOrder wo = new WorkOrder();
		wo.setLastName("Tan");
		wo.setFirstName("James");
		wo.setDateRequested(new Date());
		String phone = "7034375049";
		wo.setPhone(phone);
//		wo.setId(phone);	//purge mode
		wo.setId(Long.valueOf(phone).longValue());
		try {
			(new WorkOrderDAO()).save(wo);
			showWorkOrderList();
			assertEquals("Primary key saved is different!", phone, (new WorkOrderDAO()).get(phone).getPhone());
		} finally {
			pm.close();
		}
	}
    
    private void showWorkOrderList() {
        List<WorkOrder> results = (new WorkOrderDAO()).getList();
        if (results.iterator().hasNext()) {
            for (WorkOrder e : results) {
            	System.out.println("order from " + e.getFirstName() + ", " + e.getLastName() + " requested " + e.getDateRequested() + " served " + e.getDatePerformed());
            }
        } else {
        	System.out.println("no work order");
        }
    }
}
