<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "contents.User" %>
<%@ page import = "contents.UserDao" %>
<%@ page import = "contents.UserDaoImplementation" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Transfer Between Account</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.btn{
  		padding: 10px 20px;
  	}
  </style>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="<%=request.getContextPath() %>/home">Virtusa Bank</a>

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="<%=request.getContextPath() %>/home">Home</a>
    </li>
    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Transfer
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=request.getContextPath() %>/betwAcc">Between Accounts</a>
        <a class="dropdown-item" href="<%=request.getContextPath() %>/toSomeoneElse">To Someone Else</a>
      </div>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath() %>/profile">Profile</a>
    </li>
    <li>
    	<a class="nav-link" href="<%=request.getContextPath() %>/logout">Logout</a>
    </li>
  </ul>
</nav>
  
<div class="container">
  <% String id = (String)session.getAttribute("id"); 
  				String username = "";
  				String current = "";
  				String save = "";
		    	if(id == null){
		    		response.sendRedirect(request.getContextPath() + "/login");
		    	}
		    	else{
		    		UserDao userDao = UserDaoImplementation.getInstance();
		    		User user = null;
		    		try{
					user = userDao.findUserById(Integer.parseInt(id));
		    		}
		    		catch(Exception e){
		    			user = userDao.findUserByMail(id);
		    		}
					username = user.getFirstname()+" "+user.getLastname();
					current = user.getCurrent();
					save = user.getSavings();
		    	}
				%>
				<div class="jumbotron jumbotron-fluid">
  					<div class="container">
    				<h1 class="display-4">WITHDRAW</h1>
  					</div>
				</div>
				
				
  	<form action="<%=request.getContextPath() %>/withdraw" method="post">
  		<label>1. Choose an account you would like to withdraw money from:</label><br>
		<select class="form-control" name="fromacc">
			<option>Savings</option>
  			<option>Current</option>
		</select><br>
		<div class="form-group">
    	<label for="exampleInputAmount">2. Enter Amount:</label>
    	<input type="number" class="form-control" id="exampleInputAmount" name="amount" placeholder="Amount" required="required">
  		</div>
		<br>
		<div style="color:red">${errorMessage}</div>
        <button type="submit" class="btn btn-primary">WITHDRAW</button>
    </form>
</div>
</body>
</html>
