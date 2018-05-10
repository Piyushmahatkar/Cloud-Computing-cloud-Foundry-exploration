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
<title>New Robot</title>
<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!--  <link
	href='https://fonts.googleapis.com/css?family=Righteous|Fredoka+One'
	rel='stylesheet' type='text/css'>-->

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<style>
        #RobotCode { 
                position: absolute;
                top: 200px;
                right: 0;
                bottom: 0;
                left: 0;
            }
    </style>
</head>

<body>
<%@include file="includes/header.jsp" %>
<body>
	<!--  <div class="container">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
					<!--  <i class="fa fa-file-text"></i>	Create a New Robot,
						<%=session.getAttribute("userx")%></h1>
						</h1>
				</div>
				<!-- /.col-lg-12 
			</div>
			</div> -->
			<!-- /.row -->
			<br><br><br><br>
			<div class="row">
				<div class="col-lg-6">
<!--  <form method="post" action="editservlet"> -->
					<div class="input-group">
						<%
							Set<String> list_of_tenants = new HashSet<String>();
							Set<String> list_of_domains = new HashSet<String>();
							Set<String> list_of_robots = new HashSet<String>();
							HashMap<String, List<String>> map = new HashMap<String, List<String>>();
							HashMap<String, List<String>> domain_robot_map = new HashMap<String, List<String>>();							
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
		</script>	<br><br><h1>New Robot</h1><br>
		&nbsp;&nbsp;&nbsp;&nbsp;<label>Select User</label>
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
									//System.out.println(key);
								%>

							<option value="<%=key%>"><%=key%></option>
							<%
								}
							session.setAttribute("tenantMap", map);
							session.setAttribute("DomainMap", domain_robot_map);
							session.setAttribute("userx", "User");
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
		</script>		&nbsp;&nbsp;&nbsp;&nbsp;<label>Select Package</label>
						<select name="package" id="package" class="form-control"
							onchange="getRobots()" >
							
							<option>Select Package</option>
						
								
						</select> <br /> 
							<%} catch (Exception e) {
									out.println("wrong entry" + e);
								}
							%>

						</div>
						
						&nbsp;&nbsp;&nbsp;&nbsp;<label>Enter Robot Name. Example: MyFirstRobot. Must not contain spaces.</label>
					    <br>
						&nbsp;&nbsp;&nbsp;&nbsp;<label> Robot Name: </label> &nbsp;&nbsp;&nbsp;&nbsp;<input width="200px" name="roboName" id="robo_name"
								type="text" class="fa-la"/>
						
						</div>
						</div>
						<br>
						<script type="text/javascript">
				 function NewRobot(){
						var robotPackage=document.getElementById("package").value;
			        	 var name = document.getElementById("robo_name").value;
			        	 var user = document.getElementById("domain_name").value;
			            $.ajax({
			                url: 'newRobot',
			                type: 'POST',
			                data: "RobotInfo="+robotPackage+"-"+name+"-"+user,
			                async : false,
			                success : function(html) {
			    				console.log(html);
			    				window.location.replace("NewRobot2.jsp"); 
			                }
			            });  
			        	event.preventDefault();
			        	}
				 </script>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-success" id="create" onclick="NewRobot();">Next</button>
					</form>
				</div>
				<!-- /.col-lg-6 (nested) -->
			</div>
			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>

</body>
</html>