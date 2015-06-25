package com.appspot.cloudserviceapi.data;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import tapp.model.ServiceRegistry;

import com.appspot.cloudserviceapi.sci.dao.ServiceRegistryDAO;

public class ServiceRegistryUtil {

	/**
	 * Handle embedded token e.g. ${server1} where server1 is another entry defined in service registry.
	 * It returns the real value of the entry (service key).
	 * 
	 * Note: Limited to 10000 tokens.
	 */
	public static String handleEndPoint(String endPoint, ServiceRegistryDAO r) throws Exception {
		ServiceRegistry sr = null;
		String b = "${";
		String e = "}";
		int begin = endPoint.lastIndexOf(b);
		int end = endPoint.lastIndexOf(e); //e.g. ${private1} = private1
		int count = -1;
		String finalEndPoint = null;
		while(count <10000) { //TODO - restrict up to 10000 replacements only, should not have to restrict this!
			if(begin > -1 && end > -1) {
				String lookupService = endPoint.substring(begin + b.length(),end);
				//System.out.println("lookupService found in endPoint = " + lookupService);
				sr = (tapp.model.ServiceRegistry)r.findServiceRegistryByService(lookupService);
				if(sr != null) {
					//TBD - this could be a target for the (future's) Java closure :)
					//=== BT0001 begin - handling "legacy" bigtable column (does not exist before the new changes)
					boolean disabled = false, useDescription = false, useHTML = true;
//					try {
//						if(u.getDisabled()) {
//							disabled = true;
//						}
//					} catch (Exception e1) {
//						System.out.println("legacy entity without disabled flag: " + u);
//					}
//					try {
//						if(u.getUseDescription()) {
//							useDescription = true;
//						}
//					} catch (Exception e2) {
//						System.out.println("legacy entity without useDescription flag: " + u);
//					}
//					try {
//						if(!u.getUseHtml()) {
//							useHTML = false;
//						}
//					} catch (Exception e3) {
//						System.out.println("legacy entity without useHTML flag: " + u);
//					}
					//=== BT0001 end - handling "legacy" bigtable column (does not exist before the new changes)
					
					if(!useDescription) {
						finalEndPoint = endPoint.substring(0, begin) + sr.getEndpoint() + endPoint.substring(end + e.length(), endPoint.length());
					} else {
						finalEndPoint = endPoint.substring(0, begin) + sr.getDescription() + endPoint.substring(end + e.length(), endPoint.length());
					}
					System.out.println("Final endPoint = " + finalEndPoint);
					endPoint = finalEndPoint;
				}
			}
			count++;
			begin = endPoint.lastIndexOf(b);
			end = endPoint.lastIndexOf(e); //e.g. ${private1} = private1
		}
		
		return endPoint;
	}
	
	public static void countHit(ServiceRegistry u, ServiceRegistryDAO r, HttpServletRequest request) {
		Long temp = u.getHit();
		if(temp == null) {
			temp = new Long(0);
		}
		if(request.getParameter("incog") == null) {
			u.setHit(temp + 1L);
			u.setLastAccessed(new Date());
			System.out.println(u.getService() + " hit " + u.getHit());
			r.save(u);
		} else {
			System.out.println("incognito mode, tracking disabled");
		}
	}
	
	/**
	 * Given a URL, returns its service registry object.
	 * @param endPoint
	 * @param r
	 * @return
	 * @throws Exception
	 */
	public static ServiceRegistry getServiceRegistry(String service, ServiceRegistryDAO r) throws Exception {
		ServiceRegistry sr = null;
		sr = (tapp.model.ServiceRegistry)r.findServiceRegistryByService(service);
		if(sr == null) {
			throw new Exception("No such service registry identified by '" + service + "'");
		}
		return sr;
	}
	
	public static boolean isRedirectedEndPoint(String endPoint) throws Exception {
		if(endPoint == null) throw new Exception("EndPoint is null or empty!");
		
		boolean retVal = false;
		
		String b = "${";
		String e = "}";
		int begin = endPoint.trim().lastIndexOf(b);
		int end = endPoint.trim().lastIndexOf(e); //e.g. ${private1} = private1
		if(begin == 0 && end == (endPoint.trim().length() -1)) {
			retVal = true;
		}
		
		return retVal;
	}
}
