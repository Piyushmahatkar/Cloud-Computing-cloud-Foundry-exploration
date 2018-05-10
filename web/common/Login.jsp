

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
        	body {
        		background-image: url("http://robocode.sourceforge.net/home/robocode_logo_white.jpg");
			background-repeat: no-repeat;
			background-position: 0 0;
			background-size: cover;
			color: blue;
       		}
        	div {
        		position : absolute;
        		top : 50%;
        		left:90%;
        		transform:translate(-50%,-50%);
       		}
       		h1.serif {
        		color : MediumSeaGreen;
        		text-align : left;
        		font-size: 50px;
        		font-family:"Times New Roman", Times, serif;
        	}
        	h2 {
        		color : black;
        		text-align:centre;
        		font-family: sans-serif;
        		font-size: 30px;
        	}
        	input {
        		display: block;
        		width: 320px;
        		height: 40px;
        		padding: 5px;
        		font-size: 20px;
        		font-family : sans-serif;
        		color: blue;
        		outline: none;
        		border: 1px solid rgba(0,0,0,0.3);
        		border-radius: 5px;
        		margin-bottom: 2px;
        	}
        </style>
    </head>
    <body>
    	<h1 class= "serif">Login page</h1> 
    	<div id="login">
    	<h1>CHECK</h1>
        	<h2>SIGN IN</h2> 
        	<form action="loginservlet" method="post">
        		<input type="text" name= "username" placeholder= "username" value="">
            	<br/><input type="password" name= "password" placeholder="password" value=""> 
            	<br/><input type="submit" value="Submit" > 
        	</form> 
        	<br/>
        	<h2>REGISTER</h2>
        	<form action="UserRegister.jsp" method="post">
            	<input type="text" name="username" placeholder="username"> 
            	<br/><input type="password" name="password" placeholder="Password">
            	<br/><input type="password" name="confirm_password" placeholder="Confirm password">
            	<br/><input type="text" name="email" placeholder="email id">
            	<br/><input type="submit" value="Submit">
        	</form>
        </div>
    </body> 
</html>

