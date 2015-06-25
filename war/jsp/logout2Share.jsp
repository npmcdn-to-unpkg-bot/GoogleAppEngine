<%@ page import="app.common.Constants" %>
<%@ page import="app.controller.MovieHandler" %>

<%
//http://stackoverflow.com/questions/8013333/invalidating-session-in-jsp-servlet
HttpSession session1 = request.getSession(false);
String currentUserId = null;

if(session1 != null){
	currentUserId = (String)session1.getAttribute(Constants.UNIVERSAL_ID);
    //purge all past events
    MovieHandler.purgePastScheduledMovies(currentUserId);
    session1.invalidate();
	System.out.println("logout2Share.jsp: session cleared for user id [" + currentUserId + "]!");
} else {
    //There is no session. Redirect somewhere
	System.out.println("logout2Share.jsp: session is null, nothing is done");
}
%>