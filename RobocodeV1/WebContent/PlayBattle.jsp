<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ResultSet resultset = null;
%>
<title>New Battle</title>
<%@include file="includes/header.jsp"%>
<script src="http://code.jquery.com/jquery.min.js"></script>
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <!-- Custom CSS -->
<link href="css/freelancer.css" rel="stylesheet"type="text/css">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
  <script>
  $(function() {
    $( "#tabs" ).tabs();
  });
  </script>
  <script type="text/javascript">
    function gunCool(){
    	var num=document.getElementById("gunCool").value;
    	var floatNum=parseFloat(num);
    	if(isNaN(floatNum)){
    		alert("'Gun Cooling Rate' must be a floating point number >0 and <=0.7\nDefault value is 0.1");
    		document.getElementById("gunCool").value=0.1;
    	}
    	else if((floatNum<=0)||(floatNum>0.7)){
    		alert("'Gun Cooling Rate' must be a floating point number >0 and <=0.7\nDefault value is 0.1");
    		document.getElementById("gunCool").value=0.1;
    	}
    	else;
    		
}
    function sentryBorder(){
    	var num=document.getElementById("sentryBorder").value;
    	var intNum=parseInt(num);
    	if(isNaN(intNum)){
    		alert("'Sentry Border Size' must be an integer value >= 50.\nDefault value is 100");
    		document.getElementById("sentryBorder").value=100;
    		
    	}
    	else if(intNum<50){
    		alert("'Sentry Border Size' must be an integer value >= 50.\nDefault value is 100");
    		document.getElementById("sentryBorder").value=100;
    	
    	}
    	else{
    		document.getElementById("sentryBorder").value=intNum;
    		
    	}
}
</script>
<SCRIPT>
function getValueFromApplet(){
   var value;
	document.myForm.q.value = document.myApplet.getResult();
	value = document.myApplet.getResult();
	var RobotRankingsInfo = value.split(" "); 
	for (i = 0; i < RobotRankingsInfo.length; i++) { 
	    var each_robot_info = RobotRankingsInfo[i].split(":");
	    
	}
   if(value.length > 1)
	   {s
	   
	   	document.forms[0].submit();
	   }
   else
	   {
	   
	   }
   
   }
</SCRIPT>
</head>
<body onload="selectAll();getDomains();selectAll();getRobots();selectAll()">
<script type="text/javascript">
	function Play(){
		window.location = "PlayBattle2.jsp";
 	}
	function selectAll(){
		//document.getElementById("all").selected=true;
		$('select option:first-child').attr("selected", "selected");
	}
	</script>
	
	<br><br><br><br><br><br><br><br><br>
	
	
<div id="tabs">
<ul>
	<li onclick="gunCool();sentryBorder();"><a href="#tabs-1">Robots</a></li>
	<li><a href="#tabs-2">Rules</a></li>
</ul>
<div id="tabs-1" >
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
				url : "GetrobotDomain2",
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
		<br>	
		<table>
    <tr>
        <td width="250px"> 
        &nbsp;&nbsp;User
	<select multiple name="domain_name" id="domain_name" class="form-control" onchange="getDomains()">
	
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
	</select>
	<script type="text/javascript">
		function getRobots() {

			var x = document.getElementById("package").value;
			//alert("x value:"+x);
			$.ajax({
				url : "Getrobots2",
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
		</script></td>
        <td width="250px"> 
&nbsp;&nbsp;Package
						
						<select multiple name="package" id="package" class="form-control"
							onchange="getRobots()" ></select> <br > 
				
							<script  type="text/javascript">
							function BattleButton(){
								if (!$('selectrobots').val()){
									document.getElementById("battlebutton").disabled = false;
								}
								else
									document.getElementById("battlebutton").disabled = true;
							}
							</script></td>
							
         <td width="250px"> 
         &nbsp;&nbsp;Robots
						<select multiple name="displayrobots" id="displayrobots"
							class="form-control" >
						</select>

						<%
								} catch (Exception e) {
									out.println("wrong entry" + e);
								}
							%>
					
         </td>
         <td width="100px" align="center"><button onclick="AddtoChosen()">Add</button >
         </td>
         <td width="250px"><script  type="text/javascript">
							function AddtoChosen(){
								    $('#displayrobots option:selected').appendTo('#selectrobots');
								    BattleButton();
							}
							</script>
			&nbsp;&nbsp;Selected Robots<select multiple name="selectrobots" id="selectrobots" class="form-control" onchange="BattleButton()">
			</select>
			</td>
    </tr>
</table>		
	</div>
		
</div>		
<!-- /.col-lg-6 (nested) -->
			</div>
			<div id="tabs-2">
			<table>
			<tr>
			<td>
	Number of Rounds: <br> (integer > 0)</td><td>
	<input type="text" name="numRounds" onkeypress="return isNumberKey(event)" value="10"></td></tr>
	<tr>
	<td width="200px">Gun Cooling Rate: <br> (>0 and <=0.7)</td><td>
	<input type="text" name="gunCool" id="gunCool" value="0.1"></td></tr>
	<tr>
	<td>
	Inactivity Time: <br> (integer >= 0)</td>
	<td><input type="text" name="inactivity" onkeypress="return isNumberKey(event)" value="450"></td></tr>
	<tr><td>
	Sentry Border Size: <br> (integer >= 50)</td>
	<td><input type="text" name="sentryBorder" id="sentryBorder" value="100"></td></tr>
	<tr><td>
	Hide Enemy Names: </td><td><input type="checkbox" name="enemy" value="enemy">  </td></tr>
	</table>
	</div>			
			<!-- /.row (nested) -->
		</div>
<script language=Javascript>
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && ((charCode < 48) || charCode > 57))
      return false;

   return true;
}
function fifty(evt)
{
	var numbers = /^[0]\.[0-9]+$/; 
   if (charCode > 31 && ((charCode < 48) || charCode > 57))
      return false;

   return true;
}
</script>	
			
			
				
 <div class="container">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
					<i class="fa fa-play"></i><button disabled name="battlebutton" id="battlebutton" onclick="Play()"> Play Battle</button>
			</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<!--  <div class="row">
		 <applet 
    code="robocode.Robocode"
    name="myApplet"
	codebase = "jar"
	width = 800
    height = 600
	archive="robocode.jar">	
	<param name=Robots value="<%=session.getAttribute("robots_name")%>">
	<param name=numberofrounds value="<%=session.getAttribute("numberofrounds")%>">
	
</applet>-->
 <FORM ACTION="scoreUpdate" 
     NAME="myForm" 
     onSubmit="return getValueFromApplet()"
     method="post">
   <INPUT TYPE="hidden" VALUE="" NAME="q">
   <INPUT TYPE="submit" VALUE="Check robots ranking" >
 </FORM>


		<br />
		<br />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;
		
		
								
		
</div>
				
		<!-- /.panel-body -->
	</div>
</body>
</html>