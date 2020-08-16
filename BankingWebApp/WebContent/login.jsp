<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>login</title>
<style type="text/css">
    form, .forgot{
		margin-left: 20px;
    }
    input[type=text]{
        padding: 5px;
        max-width: 40%;
        color: #FFF;
    }
    input[type=password]{
        padding: 5px;
        max-width: 40%;
        color: #FFF;
    }
    
    a{
        text-decoration: none;
    }
    .reg{
   		margin-left: 20px;
    }
</style>
</head>
<body>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-4">Banking WebApp</h1>
    <p class="lead">This is a demo banking application that uses JSP, HttpServlets and MySQL. Login to get started.</p>
  </div>
</div>
    <form action="<%=request.getContextPath() %>/login" method="post">
    	<div class="form-group">
    	<label for="exampleInputEmail1">Email address/ User ID:</label>
    	<input type="text" class="form-control" name="userid" id="exampleInputEmail1" aria-describedby="emailHelp" required>
    	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  		</div>
    	<div class="form-group">
    	<label for="exampleInputPassword1">Password:</label>
    	<input type="password" class="form-control" name="password" id="exampleInputPassword1" required>
  		</div>
    	<button type="submit" class="btn btn-primary">Login</button>
    </form><br>
    <p class = "forgot">Forgot Password? <a href="<%=request.getContextPath() %>/forgotPassword">Click here to reset your password</a>.<br>
    <br>
    <div style="color:red">${errorMessage}</div>
	<p class = "reg">New user? <a href="<%=request.getContextPath() %>/register">Register Here</a>. 
	</body>
</html>
