<%-- 
    Document   : Logout
    Created on : Sep 14, 2017, 3:32:47 PM
    Author     : C2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% session.removeAttribute("username"); session.removeAttribute("password"); session.invalidate(); %> 
        <h1>Logout was done successfully.</h1> 
        <a href="Login.jsp">Click here to return to the login screen</a>
    </body>
</html>