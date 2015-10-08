<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>  
<html>
 	<head>  
		<title>  
			<decorator:title />
		</title>
		<decorator:head />
	</head>
  	<body>
   		<div id="header">
    		<h2><a href="/sci/index.htm?decorator=mobile&confirm=true">Desktop Version</h2>
   		</div>
    	<decorator:body />
  	</body>  
 </html>
