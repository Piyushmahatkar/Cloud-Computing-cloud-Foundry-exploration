<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="includes/dropdown.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
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

<script src="http://code.jquery.com/jquery.min.js"></script>

</head>
<body>
<%@include file="includes/header.jsp" %> 
	<!-- /.container-fluid  </nav>-->
	<!-- Header -->
	<header>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<img class="img-responsive" src="includes/robocode_logo_tanks.png"
					alt="">
				<div class="intro-text">
					<span class="name">Welcome to Robocode123!</span>
					<hr class="star-light">
					<span class="skills">Build the best, destroy the rest!</span>
				</div>
			</div>
		</div>
	</div>
	</header>
	<!--  <section id="portfolio">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2>Example Battle</h2>
				<hr class="star-primary">
			</div>
		</div>
		<iframe width="720" height="315"
			src="https://www.youtube.com/embed/8Eo0ueaywcw" frameborder="0"
			allowfullscreen></iframe>

	</div>
	</section>-->
	<footer class="text-center">
	<div class="footer-above">
		<div class="container">
			<div class="row">
				<!--  <div class="footer-col col-md-4">-->
					<h3>About Robocode</h3>
					<center>
					<p>
						Robocode is a programming game where the goal is to code a robot
						battle tank to compete against other robots in a battle arena. So
						the name Robocode is a short for "Robot code". The player is the
						programmer of the robot, who will have no direct influence on the
						game. Instead, the player must write the AI of the robot telling
						it how to behave and react on events occurring in the battle
						arena. Battles are running in real-time and on-screen.</a>
					</p>
					</center>
				<!--  </div> -->
			</div>
		</div>
	</div>
	<div class="footer-below">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">Copyright &copy; 2016</div>
			</div>
		</div>
	</div>
	</footer>
	<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
	<div class="scroll-top page-scroll visible-xs visible-sm">
		<a class="btn btn-primary" href="#page-top"> <i
			class="fa fa-chevron-up"></i>
		</a>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/freelancer.js"></script>
</body>

</html>
