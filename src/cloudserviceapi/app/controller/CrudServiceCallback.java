package cloudserviceapi.app.controller;

import javax.servlet.http.HttpServletRequest;

public interface CrudServiceCallback {

	public Object parseRequest(HttpServletRequest request);

	public String doGetItems() throws Exception;
	
	public String doGetAllItems() throws Exception;

	public Object doCreateItem(Object item) throws Exception;

	public Object doGetItem(long itemId) throws Exception;

	public Object doUpdateItem(Object item) throws Exception;

	public Object doDeleteItem(long itemId) throws Exception;
	
	public Object doDeleteItem(Object item) throws Exception;

	public Object doMigrateAllItems() throws Exception;

	public Object doMigrateAllItems(String uid) throws Exception;

	public Object doMigratePurgeItem(String uid, String keyString) throws Exception;
}
