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
  <title>Profile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="<%=request.getContextPath() %>/home">Virtusa Bank</a>

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
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
      <a class="nav-link active" href="<%=request.getContextPath() %>/profile">Profile</a>
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
  				String mail = "";
  				String fname = "", lname= "";
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
					lname = user.getLastname();
					fname = user.getFirstname();
					current = user.getCurrent();
					save = user.getSavings();
					mail = user.getEmail();
		    	}
				%>
				<div class="jumbotron jumbotron-fluid">
  					<div class="container">
    				<h1 class="display-4">EDIT PROFILE</h1><br>
    				<h5>Account Number: <%=id%></h5>
  					</div>
				</div>
		<form action="<%=request.getContextPath() %>/editProfile" method="post">
			<div class="form-group">
    		<label for="exampleInputFname">First Name:</label>
    		<input type="text" class="form-control" name="newfname" id="exampleInputFname" value="<%=fname %>" required>
  			</div>
  			
  			<div class="form-group">
    		<label for="exampleInputLname">Last Name:</label>
    		<input type="text" class="form-control" name="newlname" id="exampleInputLname" value="<%=lname %>" required>
  			</div>
  			
  			<div class="form-group">
    		<label for="exampleInputEmail">E-mail:</label>
    		<input type="text" class="form-control" name="newmail" id="exampleInputEmail" value="<%=mail %>" required>
  			</div>
  			<button type="submit" class="btn btn-primary">SAVE</button>
		</form>
</div>

</body>
</html>