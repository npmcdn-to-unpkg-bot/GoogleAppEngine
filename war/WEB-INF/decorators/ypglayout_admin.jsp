<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
	<head>  
  		<title>  
   			<decorator:title default="General" />
  		</title>
  		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="/css/ypgstyle.css" />
	  	<decorator:head />
 	</head>
<body style="overflow-x: hidden;">

	<jsp:include page="/gu/header.jsp"></jsp:include>

	<decorator:body />

</body>
</html>