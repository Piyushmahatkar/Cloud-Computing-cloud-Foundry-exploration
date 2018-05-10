<%-- 
    Document   : Registration
    Author     : Arunabha
    Group      : C2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<html> 
<head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Registration page</title> 
</head> 
<body> 
<%@ page import= "java.sql.*" %>
<%@ page import= "javax.sql.*" %>
<% 
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/robocode", "root", "root");
	Statement smt = con.createStatement();
	ResultSet rs;
    String username=request.getParameter("username"); 
    String password=request.getParameter("password");
    String confirm_password=request.getParameter("confirm_password");
    String email=request.getParameter("email");
    //if(password.equals(confirm_password)) { 
        //TODO create the user in the database and continue to app
        int i = smt.executeUpdate("insert into loginstats values('"+username+"','"+password+"','"+email+"')");
        out.println("Registration successfull");
        response.sendRedirect("Welcome.jsp");
         
        //response.sendRedirect("Login.jsp"); 
%>
</body>
</html>