<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
	ResultSet resultset = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Robot</title>
 
<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Righteous|Fredoka+One'
	rel='stylesheet' type='text/css'>

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
</head>

<body>

<%@include file="includes/header.jsp" %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1>View Robot</h1>
	<br>
	<div class="dropdown">

		<!-- trigger button -->
		<button>File</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li onclick="New()"><a href="#open">New Robot</a></li>
			<li onclick="Edit()"><a href="#open">Edit Robot</a></li>
			<li onclick="View()"><a href="#open">View Robot</a></li>
		
		</ul>

	</div>
	<br><br>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-6">
		<form method="post" action="editservlet">
				<div class="form-group">
					<div class="input-group">
						<%
							Set<String> list_of_tenants = new HashSet<String>();
							Set<String> list_of_domains = new HashSet<String>();
							Set<String> list_of_robots = new HashSet<String>();
							HashMap<String, List<String>> map = new HashMap<String, List<String>>();
							HashMap<String, List<String>> domain_robot_map = new HashMap<String, List<String>>();							
							//	String name = "Esther";
								try {
									String connectionURL = "jdbc:mysql://localhost:3306/robocode";
									Class.forName("com.mysql.jdbc.Driver").newInstance();
									Connection connection = DriverManager.getConnection(connectionURL, "root",
											"root");

									Statement statement = connection.createStatement();
									String selectString="SELECT userID, packageID, robotID from robot";
									resultset = statement
											.executeQuery(selectString);
									
											%>
<script type="text/javascript">
		function getDomains() {

			var x = document.getElementById("domain_name").value;
			$.ajax({
				url : "GetrobotDomain",
				data : "tenant_name=" + x + "",
				type : 'POST',
				async : false,
				success : function(html) {
					console.log("html:" + html);
					$("#package").html(html);
				},
				error : function(html) {
					console.log("error html:" + html);
				}
			});
		}	
		</script>	
						<select name="domain_name" id="domain_name" class="form-control" onchange="getDomains()"
							>
							<option>Select User</option>

							<%
									while (resultset.next()) {
										list_of_tenants.add(resultset.getString(1));
										list_of_domains.add(resultset.getString(2));
										list_of_robots.add(resultset.getString(3));
										String value1 = resultset.getString(1);
										String value2 = resultset.getString(2);
										String value3 = resultset.getString(3);
										List<String> value = map.get(value1);
										if (value == null) {
											map.put(value1, new ArrayList<String>());
											map.get(value1).add(value2);
										} else {
											value = map.get(value1);
											if (!value.contains(value2)) {
												map.get(value1).add(value2);
											}

										}
										value = domain_robot_map.get(value2);
										if (value == null) {
											domain_robot_map.put(value2, new ArrayList<String>());
											domain_robot_map.get(value2).add(value3);
										} else {
											value = domain_robot_map.get(value2);
											if (!value.contains(value3)) {
												domain_robot_map.get(value2).add(value3);
											}

										}
									}
								
								Iterator iterator = list_of_tenants.iterator();
								while (iterator.hasNext()) {
									String key = (String) iterator.next();										
								%>

							<option value="<%=key%>"><%=key%></option>
							<%
								}
							session.setAttribute("tenantMap", map);
							session.setAttribute("DomainMap", domain_robot_map);
								%>
						</select> <br /> 
						<script type="text/javascript">
		function getRobots() {

			var x = document.getElementById("package").value;
			//alert("x value:"+x);
			$.ajax({
				url : "Getrobots",
				data : "domain_name=" + x + "",
				type : 'POST',
				async : false,
				success : function(html) {
					console.log("html:" + html);
					$("#displayrobots").html(html);
				},
				error : function(html) {
					console.log("error html:" + html);
				}
			});
		}	
		</script>		
						<select name="package" id="package" class="form-control"
							onchange="getRobots()" >
							<option>Select Package</option>
						
								
						</select> <br /> 
						<script type="text/javascript">
							function RobotNames(value)
							{
							    
								   var x = document.getElementById("domain_name").value;
							       	 var y = document.getElementById("package").value;
							       	console.log("Hi");
							            $.ajax({
							    			url : "editservlet",
							    			data: "domain_name="+x+"-"+y+"-"+value,
							    			type : 'POST',
							    			async : false,
							    			success : function(html) {
							    				$("#RobotCode").html(html);
							    				console.log(html);
							    			}
							    		});
							    		event.preventDefault();
							         
							        
							}
							</script>
						<select name="displayrobots" id="displayrobots" onchange="RobotNames(this.value);"
							class="form-control" >
							<option>Select Robot</option>
							
						</select>

						<%
								} catch (Exception e) {
									out.println("wrong entry" + e);
								}
							%>
						<br /> <br /> <br />

					</div>
				</div>


			</form>
			<form ><!-- action="updateRobot" method="post"> -->
				<div class="form-group" id="RobotCodeh"
					style="position: absolute; top: 150px; left: 15px;">
					<textarea disabled style="display: block;" name="editor" id="RobotCode"
						rows="16" cols="100">
					</textarea>
				</div>
				<div>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				<script type="text/javascript">
				 function SaveRobot(){
				
			        	 var x = document.getElementById("RobotCode").value;
			            $.ajax({
			                url: 'updateRobot',
			                type: 'POST',
			                data: "RobotCode="+x,
			                async : false,
			                success : function(html) {
			    				$("#RobotCode").html(html);
			    				console.log(html);
			    				$('<div id="alert">Successfully Updated</div>').appendTo(document.body);
			                }
			            });  
			        	event.preventDefault();
			        	}
				 </script>
				<!-- <a href="welcome.jsp">Done</a>-->
				<input type="button" style="height:50px;width:70px" value="Done" onclick="window.location='welcome.jsp'" >
				
				</div>
			</form>
						

		</div>
		<!-- /.col-lg-6 (nested) -->
	</div>
</body>
</html>