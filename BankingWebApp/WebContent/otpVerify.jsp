<%@page import="contents.User"%>
<%@page import="contents.UserDaoImplementation"%>
<%@page import="contents.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Transfer Verification</title>
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
    				<h1 class="display-4">We've sent you a verification code in your e-mail..</h1>
    				<p>Kindly check your mail to verify.</p>
  					</div>
				</div>
      	<form action="<%=request.getContextPath() %>/VerifyOTP" method = "post">
      		<div class="form-group">
    		<label for="exampleInputOTP">Enter OTP:</label>
    		<input type="number" class="form-control" name="enteredotp" id="exampleInputOTP" required>
  			</div>
      		<button type="submit" class="btn btn-primary">VERIFY</button><br><br>
      	</form>
      
   </body>
</html>