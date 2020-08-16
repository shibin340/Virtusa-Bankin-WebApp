<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "contents.User" %>
<%@ page import = "contents.UserDao" %>
<%@ page import = "contents.UserDaoImplementation" %>
<%@ page import = "contents.Transaction" %>
<%@ page import = "contents.TransactionDao" %>
<%@ page import = "contents.TransactionDaoImplementation" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
				<div class="jumbotron jumbotron-fluid">
  					<div class="container">
    				<h1 class="display-4">Previous Transactions</h1>
  					</div>
				</div>                                                                                   
  <div class="table-responsive">          
  <table class="table">
  	<%
  		String id = (String)session.getAttribute("id"); 
		String username = "";
		String current = "";
		String save = "";
		if(id == null){
			response.sendRedirect(request.getContextPath() + "/login");
		}
		UserDao userDao = UserDaoImplementation.getInstance();
		TransactionDao transDao = TransactionDaoImplementation.getInstance();
		User user = null;
		try{
		user = userDao.findUserById(Integer.parseInt(id));
		}
		catch(Exception e){
			user = userDao.findUserByMail(id);
		}
		List<Transaction> trans = transDao.findTransactionsById(user.getId());
		
  	%>
  	
    <thead>
      <tr>
        <th>Transaction ID</th>
        <th>TYPE</th>
        <th>DATE</th>
        <th>STATUS</th>
        <th>AMOUNT</th>
        <th>FROM</th>
        <th>TO</th>
      </tr>
    </thead>
    <tbody>
    <% for(Transaction t : trans){
      	%>
      <tr>
        <td><%=t.getTransaction_id() %></td>
        <td><%=t.getType() %></td>
        <td><%=t.getTime() %></td>
        <td><%=t.getStatus() %></td>
        <td><%=t.getAmount() %></td>
        <td><%=t.getUser_fromid()%></td>
        <td><%=t.getUser_toid()%></td>
      </tr> 
      <% }%>
    </tbody>
  </table>
  </div>
</div>

</body>
</html>