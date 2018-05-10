<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String enteredUsername=request.getParameter("username");
        String enteredPassword=request.getParameter("password");
    	
			try {
				String connectionURL = "jdbc:mysql://localhost:3306/robocode";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connection = DriverManager.getConnection(connectionURL, "root",
						"root");

				Statement statement = connection.createStatement();
				String selectString="SELECT ID,UserName, Password, Role from User where UserName='"+enteredUsername+"'";
				resultset = statement
						.executeQuery(selectString);
			}
					
		if(resultset.first()){
			String UserName = resultset.getString("UserName");
            int ID= resultset.getInt("ID");
		}
       
       
               
        if((username.equals("admin") && password.equals("admin")))
            {
            session.setAttribute("username",username);
            response.sendRedirect("welcome.jsp");
            }
        else
            response.sendRedirect("Error.jsp");
        %>
    </body>
</html>
