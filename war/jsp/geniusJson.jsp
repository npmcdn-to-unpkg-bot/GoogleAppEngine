<%@ page import="cloudserviceapi.service.manager.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="app.common.Constants" %>
<%@ page import="com.appspot.cloudserviceapi.dto.Geniu" %>

<%
//http://stackoverflow.com/questions/8013333/invalidating-session-in-jsp-servlet
HttpSession session1 = request.getSession(false);
String currentUserId = null;

if(session1 != null){
	currentUserId = (String)session1.getAttribute(Constants.UNIVERSAL_ID);
	List<Geniu> al = new ArrayList<Geniu>();
	Geniu g1 = new Geniu();
	g1.setId(1L);
	g1.setWhat("Equity Share Capitals");
    al.add(g1);
    Geniu g2 = new Geniu();
	g2.setId(2L);
	g2.setWhat("Calls In Arear");
    al.add(g2);

//    ServiceRegistryManager beanManager = new ServiceRegistryManagerImpl();
//    al = beanManager.getServiceRegistrys();
	GeniusManager beanManager = new GeniusManagerImpl();
    al = beanManager.getGenius();
/*
*/

    ArrayList<Map> al2 = new ArrayList<Map>();
    for(Geniu account : al) {
       HashMap hm = new HashMap();
       hm.put("ID"+ account.getId(), "name"+account.getWhat());
       al2.add(hm);
    }

    Gson g = new Gson();
    System.out.println(g.toJson(al2));
    //session1.invalidate();
	System.out.println("geniusJson.jsp: done for user [" + currentUserId + "]!");
} else {
    //There is no session. Redirect somewhere
	System.out.println("geniusJson.jsp: session is null, nothing is done");
}
%>