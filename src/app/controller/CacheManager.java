package app.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CacheManager {
	private static String separator = ",";
	
	public static String getUserCacheKey(String uid, String filter, long pageNumber) {
		return uid + separator + filter + pageNumber;
	}

	public static void clearUserCacheById(String uid, HashMap cache) {
		Iterator it = cache.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        String[] tk = pairs.getKey().toString().split(separator);
	        if(tk[0] != null && tk[0].equals(uid)) {
	        	it.remove(); // avoids a ConcurrentModificationException
	        }
	    }
	}
	
}
