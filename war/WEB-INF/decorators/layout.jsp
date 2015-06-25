<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<html>
	<head>  
  		<title>  
   			GAE - <decorator:title default="General" />
  		</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="/sgc/css/style.css" />
		<link rel="stylesheet" href="/sgc/html/pc/css/style.css" type="text/css" media="screen, projection, tv" />
  		<decorator:head />
 	</head>
  	<body style="overflow-x: hidden;">
  		<div>
   			<div>
   				<% if(request.getRequestURL().indexOf("/sgc/") > -1) { %>
    			<label><a href="/sgc/jsp/staff.jsp">Management Console</a></label>
				<% } %>
   			</div>
   				<% if(request.getRequestURL().indexOf("/sgc/") > -1) { %>
			<jsp:include page="/sgc/jsp/header.jsp"/>
				<% } %>
   			<decorator:body />  
   		</div>
  	</body>
</html>
