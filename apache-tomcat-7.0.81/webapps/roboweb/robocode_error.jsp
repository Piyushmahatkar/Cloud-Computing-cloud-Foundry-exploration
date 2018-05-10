<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>  
<%@page import="java.io.BufferedReader"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="includes/header_link.jsp"%>
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Robocode Error Page</title>
</head>
<body>
<div class="container">
<h1 class="page-header">
<i class="fa fa-pencil-square-o"></i> Error in Robot,<%=session.getAttribute("userx")%></h1>

<%
	HashMap<String, BufferedReader> errors = (HashMap<String, BufferedReader>) session.getAttribute("errors_map");
	Set error_keys = errors.keySet();
	Iterator<String> iterator = error_keys.iterator();
%>

<% 
	while(iterator.hasNext()){
		String robot_name = iterator.next();
		BufferedReader brr = errors.get(robot_name);
		String line = null;
%>
<H1>Robot Name: <%=robot_name %></H1>
<textarea style="display: block;" name="editor" id="RobotCode" rows="16" cols="100">
<% 
		while ((line = brr.readLine()) != null) {
%>
		<%=line %>
<% 			
		}
	}
%>
</textarea>

</div>
</body>
</html>