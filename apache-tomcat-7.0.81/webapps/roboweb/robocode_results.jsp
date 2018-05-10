<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Robocode Results</title>
<%@include file="includes/header.jsp"%>
<%@page import="java.util.*"%>
<script src="http://code.jquery.com/jquery.min.js"></script>
</head>
<body>
<div class="container">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
					<i class="fa fa-play"></i> Robots Ranking</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<%  
					// retrieve your list from the request, with casting 
					
				Map<Integer, List<String>> result_value = (Map<Integer, List<String>>) session.getAttribute("robot_rank");
				
					System.out.println("The value is" + result_value);
					// print the information about every category of the list
					
				%>

<table width="59%" border="1">
<tr>
<td>Robots</td>
<td>Robot Points</td>
<td>Ranking</td>
</tr>
<%
Set setOfKeys = result_value.keySet();
Iterator iterator = setOfKeys.iterator();
List<String> value;
int counter = 0;
int lastcounter = 0;
Integer lastkey = 0;
while (iterator.hasNext()) {
	Integer key = (Integer) iterator.next();
	value = result_value.get(key);
	System.out.println("Key: "+ key+", Value: "+ value);
	for(int i=0; i< value.size(); i++){
		if(i == 11){
			break;
		}
		if(lastkey != key){
			if(lastcounter == 0){
				counter += 1;
			}
			else
			{
				counter = lastcounter +1;	
			}
			
			lastkey  = key;
			lastcounter = 0;
		}
		else{
			if(lastcounter == 0){
				lastcounter +=  counter ;	
			}
			else{
				lastcounter +=  1 ;
			}
			
			
		}
%>
<tr>
<td><%= value.get(i) %></td>
<td><%= key %></td>
<td><%= counter %>
	</td>
</tr>
<% 
	}
} 
%>
</table>

		
		
								
		
</div>
				<!-- /.col-lg-6 (nested) -->
			</div>
			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>
</body>
<%@include file="includes/fotter.jsp"%>
</html>