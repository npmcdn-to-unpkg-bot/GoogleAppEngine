<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<html>
 	<head>  
	<title>  
	GAE - <decorator:title default="Main" />
	</title>
	<decorator:head />
	</head>
  	<body>
   		<div id="content">
    		<decorator:body />
   		</div>
  	</body>  
 </html>