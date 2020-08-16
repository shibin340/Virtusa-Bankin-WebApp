package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;

@WebServlet(urlPatterns = {"/login", "/"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserDao userDao = UserDaoImplementation.getInstance();
     
    public LoginController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	String userId = request.getParameter("userid");
    	String password = request.getParameter("password"); 
    	ResultSet resultSet = null;
    	try
    	{
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "5566");
    	Statement st=conn.createStatement();

    	resultSet = st.executeQuery("select * from auth_users where idauth_users = '"+userId+"' or auth_email = '"+userId+"' and auth_password = '"+password+"'");
    	if(resultSet.next()){
    		//response.sendRedirect(request.getContextPath() + "/home");
    		request.getSession().setAttribute("id", userId);
    	    response.sendRedirect(request.getContextPath() + "/home");
    	}
    	else {
    		request.setAttribute("errorMessage", "Invalid userId or password");
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    	}
    	}
    	catch(Exception e)
    	{
    	System.out.print(e);
    	e.printStackTrace();
    	}
    }
}