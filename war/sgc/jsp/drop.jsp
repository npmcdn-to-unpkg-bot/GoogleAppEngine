<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="guestbook.Greeting" %>
<%
try { Greeting.init("DROP TABLE 'employee'");out.println("1"); } catch(Exception e) {e.printStackTrace();}
try { Greeting.init("DROP TABLE 'customer'");out.println("2"); } catch(Exception e) {e.printStackTrace();}
try { Greeting.init("DROP TABLE 'workorder'");out.println("3"); } catch(Exception e) {e.printStackTrace();}
%>
<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>

  <body>

	<p>DESTROYED</p>

  </body>
</html>
