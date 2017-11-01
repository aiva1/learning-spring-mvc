<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> <!--  default is HTML5 -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Yahoo from JSP</title>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
	<body>
		<div class="container">
			<p><font color="red">${errorMessage}</font></p>
			
			<form class="form-horizontal" action="/login" method="POST">
			
				<div class="form-group">
					<label>Name:</label>
					<input class="form-control" type="text" name="name" placeholder="Enter username"/> 
				</div>
				
				<div class="form-group">
					<label>Password:</label>
					<input class="form-control" type="password" name="password" placeholder="Enter password"/> 
				</div>  
				
				<div class="form-group">
					<input class="btn btn-default" type="submit" value="Login"/>
				</div>
				
			</form>
		
		</div>
	<!-- NOTE: js are added just before the closing body tag -->
	<script src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>

<!-- valid user -> welcome.jsp -->
<!--  invalid user -> go back to login.jsp (the login page)  -->