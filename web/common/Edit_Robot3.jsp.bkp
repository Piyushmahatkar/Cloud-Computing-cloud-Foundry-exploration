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
<title>Edit Robot</title>
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
<style>
        #RobotCode { 
                position: absolute;
                top: 175px;
                right: 0;
                bottom: 0;
                left: 0;
            }
    </style>
</head>

<body>
<%@include file="includes/header.jsp" %>
<!--  <body id="page-top" class="index">
	<!-- /.container-fluid  </nav> -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script type="text/javascript">
	function Open(){
		window.open("Edit_Robot.jsp",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	}
	</script>
	<div class="dropdown">

		<!-- trigger button -->
		<button>File</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li onclick="Open()"><a href="#open">Open</a></li>
			<li onclick="SaveRobot();"><a href="#save">Save</a></li>
		</ul>

	</div>
	<div class="dropdown">
		<button>Edit</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li><a href="#Undo">Undo</a></li>
			<li><a href="#Redo">Redo</a></li>
			<li><a href="#Copy">Copy</a></li>
			<li><a href="#Paste">Paste</a></li>
			<li><a href="#Find">Find</a></li>
			<li><a href="#SelectAll">Select All</a></li>
		</ul>
	</div>

	<script type="text/javascript">
						
		function Compile() {
			var x =editor.getValue();
			var y = document.getElementById("displayrobots").value;
			var z = document.getElementById("package").value;
			$.ajax({
				url : 'compileservlet',
				type : 'POST',
				data : "RobotCode=" + y +"blah" + z,
				async : false,
				success : function(html) {
					editor.getSession().setValue(html);
					console.log(html);
					 $("#message").fadeIn(200);
	    				document.getElementById('message').innerHTML = 'Successfully compiled';
	    				setTimeout(fade_out, 5000);
				}
			});
			event.preventDefault();
		}
	</script>
	<div class="dropdown">
		<button>Compiler</button>
		<ul class="dropdown-menu">
			<li id="compileButton" onclick="Compile();"><a href="#">Compile</a></li>
			<li><a href="#">Options</a></li>
		</ul>
	</div>

	<div class="dropdown">
		<button>Help</button>
	</div>

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
							            $.ajax({
							    			url : "editservlet",
							    			data: "domain_name="+x+"-"+y+"-"+value,
							    			type : 'POST',
							    			async : false,
							    			success : function(html) {
							    				editor.getSession().setValue(html);
							    				//$("#RobotCode").html(html);
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
				<script type="text/javascript">
				var fade_out = function() {
					  $("#message").fadeOut().empty();
					}
				</script>
			</form>
			<div id="message">
			 Edit Robot Here
			</div>
			  
			 <div id="RobotCode">
       
    </div>

    <script src="http://d1n0x3qji82z53.cloudfront.net/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
    <script>
        var editor = ace.edit("RobotCode");
        editor.setTheme("ace/theme/clouds");
        editor.getSession().setMode("ace/mode/java");
    </script>
			<!--  <form >
				<div class="form-group" id="RobotCodeh"
					style="position: absolute; top: 150px; left: 15px;">
					<textarea style="display: block;" name="editor" id="RobotCode"
						rows="16" cols="100">
					</textarea>
				</div>-->
				<div>
				 <br><br><br><br><br><br>  <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
				<script type="text/javascript">
				 function SaveRobot(){
				
			        	 var x = editor.getValue();
			            $.ajax({
			                url: 'updateRobot',
			                type: 'POST',
			                data: "RobotCode="+x,
			                async : false,
			                success : function(html) {
			                	editor.getSession().setValue(html);
			    				console.log(html);
			    				 $("#message").fadeIn(200);
			    				document.getElementById('message').innerHTML = 'Successfully updated';
			    				setTimeout(fade_out, 5000);
			                }
			            });  
			        	event.preventDefault();
			        	}
				 </script>
				<!--  <button type="submit" class="btn btn-success" id="saveButton" onclick="SaveRobot();">Save</button>-->
				</div>
			
			<%String message = (String) request.getAttribute("message"); 
			
			if(message != null) {
				%>
				<%=message %>
				<%} %> 
	

		</div>
		<!-- /.col-lg-6 (nested) -->
	</div>



</body>
</html>