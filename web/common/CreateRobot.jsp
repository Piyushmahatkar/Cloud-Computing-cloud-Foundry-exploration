<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Robot</title>
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
<body id="page-top" class="index">
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#page-top">Robocode</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<!--  <li class="page-scroll"><a href="#create"></a>
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							Create <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Robot</a></li>
							<li><a href="#">User</a></li>
							<li><a href="#">Domain</a></li>
						</ul>
					</div></li>
					<li class="page-scroll"><a href="#EDIT"></a>
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							Edit <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Robot</a></li>
							<li><a href="#">User</a></li>
							<li><a href="#">Domain</a></li>
						</ul>
					</div></li>
					<li class="page-scroll"><a href="#create"></a>
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							View <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Robot</a></li>
							<li><a href="#">User</a></li>
							<li><a href="#">Domain</a></li>
						</ul>
					</div></li>-->
				<li class="page-scroll"><a href="#create">New</a></li>
				<li class="page-scroll"><a href="#edit">Edit</a></li>
				<li class="page-scroll"><a href="#view">View</a></li>
				<li class="page-scroll"><a href="#play">Play Battle!</a></li>
				<li class="page-scroll"><a href="#view">Settings</a></li>
				<li class="page-scroll"><a href="#view"><img
						class="img-responsive" src="img/power button.png" alt=""
						ALIGN="RIGHT" hspace="5">Logout</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="dropdown">

    <!-- trigger button -->
    <button>File</button>

    <!-- dropdown menu -->
    <ul class="dropdown-menu">
        <li><a href="#new">New</a></li>
        <li><a href="#open">Open</a></li>
        <li><a href="#save">Save</a></li>
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
    <div class="dropdown">
<button>Compiler</button>
      <ul class="dropdown-menu">
			<li><a href="#">Compile</a></li>
				<li><a href="#">Options</a></li>
				</ul>
				</div>
				
			<div class="dropdown">
<button>Play Battle</button>
</div>
			 <div class="dropdown">
<button>Help</button>
</div>
		
	<div class="row">
		<div class="col-lg-6">
			<form>
				<textarea style="display: block;" name="editor" rows="20" cols="120">
package eg;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * MyFirstRobot - a robot by (your name here)
 */
public class MyFirstRobot extends Robot
{
	/**
	 * run: MyFirstRobot's default behavior
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
				<button type="submit" class="btn btn-success" id="saveButton">Save</button>

			</form>
			<!-- /.col-lg-6 (nested) -->
		</div>
		<!-- /.row (nested) -->
	</div>
	<!-- /.panel-body -->


</body>
</html>