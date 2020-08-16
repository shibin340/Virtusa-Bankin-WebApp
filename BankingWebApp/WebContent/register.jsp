<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Register</title>
 
<style type="text/css">
    form{
		margin-left: 20px;
    }
    input[type=text], input[type=email], input[type=password]{
        max-width: 60%;
    }
    .curr{
    	max-width: 60%
    }
    a{
        text-decoration: none;
    }
    .log{
    	margin-left: 15px;
    }
</style>
</head>
<body>
    <div class="jumbotron jumbotron-fluid">
  	<div class="container">
    <h1 class="display-4">Banking WebApp</h1>
    <p class="lead">This is a demo banking application that uses JSP, HttpServlets and MySQL. Register to get started.</p>
  	</div>
	</div>
    <c:url value="/user/register" var="registerUrl" />
    <form action="<%=request.getContextPath() %>/register" method="post">
        <div class="form-group">
    	<label for="exampleInputFname">First Name:</label>
    	<input type="text" class="form-control" name="firstName" id="exampleInputFname" required>
  		</div>
        
        <div class="form-group">
    	<label for="exampleInputLname">Last Name:</label>
    	<input type="text" class="form-control" name="lastName" id="exampleInputLname" required>
  		</div>
        
        <div class="form-group">
    	<label for="exampleInputEmail1">Email address:</label>
    	<input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" required>
    	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  		</div>
        
        <div class="form-group">
    	<label for="exampleInputPassword1">Password:</label>
    	<input type="password" class="form-control" name="password" id="exampleInputPassword1" required>
  		</div>
  		
  		<div class="form-group">
    	<label for="exampleInputPassword2">Confirm Password:</label>
    	<input type="password" class="form-control" name="cpassword" id="exampleInputPassword2" required>
  		</div>
        
        <label>Select Your Base Currency:</label>
        <select class="form-control curr" name = "base">
  		<option>INR</option>
  		<option>USD</option>
  		<option>EUR</option>
  		<option>GBP</option>
  		<option>AUD</option>
  		<option>CAD</option>
  		<option>SGD</option>
  		<option>JPY</option>
  		<option>RMB</option>
  		<option>CHF</option>
		</select>
        <br>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
    <br> 
    <div style="color:red">${errorMessage}</div>
    <p class= "log">Already have an account? <a href="<%=request.getContextPath() %>/login">Login Here</a>. </body>
</body>
</html>