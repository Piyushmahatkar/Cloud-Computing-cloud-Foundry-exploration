<%@page import="DTO.*"%>
<%
	String uri = request.getRequestURI();

String pageName = uri.substring(uri.lastIndexOf("/")+1);

String userx_sess = (String) session.getAttribute("userx");
String domainx_sess = (String) session.getAttribute("domainx");
UserDTO userobj_sess = (UserDTO)session.getAttribute("userDTO");
String userpackagex_sess = (String) session.getAttribute("userpackagex");

	
	if((userx_sess!=null) && ( (pageName.equals("")==true) || (pageName.equals("index.jsp")==true) ))
	{
		response.sendRedirect("welcome.jsp");
	}
	else if((userx_sess==null) && (pageName.equals("index.jsp")==false))
	{
		response.sendRedirect("index.jsp");
	}
%>
