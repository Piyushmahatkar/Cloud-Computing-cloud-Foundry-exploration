<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="DTO.RobotDTO"%>
<%
	ResultSet resultset = null;
%>
<%
	RobotDTO objRobot = new RobotDTO();
	//objRobot.setRobot_name(request.getParameter("robotName").toString());
	//objRobot.setRobot_desc(request.getParameter("robotPack").toString());
	if (session.getAttribute("objCurrentRobot") != null)
		objRobot = (RobotDTO) session.getAttribute("objCurrentRobot");

	String robotname = objRobot.getRobotName();
	String packageName = objRobot.getPackageId();
	System.out.println(robotname +" "+packageName);
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
<link
	href='https://fonts.googleapis.com/css?family=Righteous|Fredoka+One'
	rel='stylesheet' type='text/css'>

<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<style>
        #RobotCode { 
                position: absolute;
                top: 290px;
                right: 0;
                bottom: 0;
                left: 0;
            }
    </style>
</head>

<body onload ="onloadPage()">
<script type="text/javascript">
function onloadPage(){
	 document.getElementById('textArea').style.display = "none";
	 document.getElementById('name').style.display = "none";
	 document.getElementById('package').style.display = "none";
}
</script>
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
	<br>
	<br>
	<script type="text/javascript">
	
	function Undo(){
		editor.session.getUndoManager().undo(false);
	}
	
	function Redo(){
		editor.session.getUndoManager().redo(false);
	}
	function New(){
		window.open("NewRobot.jsp",null,
		"height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	}
	</script>
	<div class="dropdown">

		<!-- trigger button -->
		<button>File</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li onclick="New()"><a href="#New">New</a></li>
			<li onclick="SaveRobot()"><a href="#Save">Save</a></li>
		</ul>

	</div>
	<div class="dropdown">
		<button>Edit</button>

		<!-- dropdown menu -->
		<ul class="dropdown-menu">
			<li><a href="Undo();">Undo</a></li>
			<li><a href="Redo();">Redo</a></li>
			<li><a href="#Copy">Copy</a></li>
			<li><a href="#Paste">Paste</a></li>
			<li><a href="#Find">Find</a></li>
			<li><a href="#SelectAll">Select All</a></li>
		</ul>
	</div>

	<script type="text/javascript">	
		function Compile(){
			SaveRobot();
			//window.alert("Compile Robot JS");
			var x =document.getElementById('RobotCode').value;
			var y = document.getElementById('name').value;
			var z =  document.getElementById('package').value;
			$.ajax({
				url : 'compileservlet',
				type : 'POST',
				data : "RobotCode=" + y + "blah" + x + "blah" + z,
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
			<li id="compileButton" onclick="Compile()"><a href="#Compile">Compile</a></li>
			<li><a href="#">Options</a></li>
		</ul>
	</div>
	

	<div class="dropdown">
		<button>Help</button>
	</div>
	<!-- /.row -->
	
				<script type="text/javascript">
				var fade_out = function() {
					  $("#message").fadeOut().empty();
					};
				</script>
		
			<div id="message" >
			<script type="text/javascript">
			document.getElementById('message').style.marginLeft = "20px";
			</script>
			</div>
			  <br>
			  <br>
			  <br>
	<br>
	<br>
	<br>
			  <br>
			  <br>
	<br> <form method="post" id="divdata" action="updateNewRobot">
			 <textarea id="RobotCode" margin-left: 20px "  style="display: block;" name="editor" 
						rows="30" cols="100">
package <%=packageName%>;

import robocode.*;
import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
//import java.awt.Color;

// API help: http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * <%=robotname%>- a robot by <%=session.getAttribute("userx")%>
 */
public class <%=robotname%> extends Robot{
	/**
	 * run: <%=robotname%>default behavior
	 */
	public void run() {
	// Initialization of the robot should be put here

	// After trying out your robot, try uncommenting the import at the top,
	// and the next line:

	// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

	// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
    </textarea>
	<textarea id="textArea" name="textArea"></textarea>
	</form>
    <script src="http://d1n0x3qji82z53.cloudfront.net/src-min-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
    <script>
        var editor = ace.edit("RobotCode");
        editor.setTheme("ace/theme/clouds");
        editor.getSession().setMode("ace/mode/java");
        var undo=new ace.UndoManager();
        editor.getSession().setUndoManager(undo);
    </script>
    <textarea id="name" name="name"><%=robotname%></textarea>
    <textarea id="package" name="package"><%=packageName%></textarea>
    <script type="text/javascript">
    function SaveRobot2() {
        var code = editor.getValue();
        document.getElementById('textArea').style.display = "block";
        document.getElementById('textArea').value=code;
        document.getElementById("RobotCode").submit();
    }
				 function SaveRobot(){
					// window.alert("Inside Save Robot");
					// window.alert(document.getElementById("RobotCode").value);
				      	 var x = document.getElementById("RobotCode").value;
			        
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
			       	 window.alert("Inside Save Robot");
			        	event.preventDefault();
			        	}
				 </script>
</body>
</html>