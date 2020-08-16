<%@page import="contents.User"%>
<%@page import="contents.UserDaoImplementation"%>
<%@page import="contents.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Forgot Password</title>
      <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style type="text/css">
    form{
		margin-left: 20px;
    }
    
</style>
   </head>
   
   <body>
         <div class="jumbotron jumbotron-fluid">
  					<div class="container">
    				<h1 class="display-4">Forgot Password</h1>
  					</div>
				</div>
		
      	<form action="<%=request.getContextPath() %>/forgotPassword" method = "post">
      		<div class="form-group">
    		<label for="exampleInputMail">Enter Your E-mail ID to reset your password:</label>
    		<input type="email" class="form-control" name="forgotmail" id="exampleInputMail" required>
  			</div>
      		<button type="submit" class="btn btn-primary">Send Reset Code</button><br><br>
      	</form>
      	<br>
    	<div style="color:red">${errorMessage}</div>
      	
   </body>
</html>