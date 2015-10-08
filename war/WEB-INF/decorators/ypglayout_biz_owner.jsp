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
<body id="ypgbody1" style="overflow-x: hidden;">
	<div id="example">
		<jsp:include page="/gu1/header.jsp"></jsp:include>
	
		<div id="ex_middle">
		
			<jsp:include page="/gu1/left.jsp"></jsp:include>
		
			<div id="ex_main1">
				<decorator:body/>
			</div>
			  <!-- make the middle region's background color expand -->
			  <div class="clear"></div>
		</div>
	
		<jsp:include page="/gu/footer.jsp"></jsp:include>
	
	</div>
</body>
</html>