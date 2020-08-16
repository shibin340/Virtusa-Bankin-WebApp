package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import contents.Transaction;
import contents.TransactionDao;
import contents.TransactionDaoImplementation;
import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;

@WebServlet("/register")
public class UserRegistrationController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    private UserDao userDao = UserDaoImplementation.getInstance();
    private TransactionDao transDao = TransactionDaoImplementation.getInstance();
 
    public UserRegistrationController() {
        // Do Nothing
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String current = "10000";
        String save = "10000";
        String base = request.getParameter("base");
        int flag = 0;
        if(password.length()<6) {
        	flag = 0;
    		request.setAttribute("errorMessage", "Password too short.");
    		request.getRequestDispatcher("register.jsp").forward(request, response);
    	}
        else if(!cpassword.equals(password)) {
        	flag = 0;
        	request.setAttribute("errorMessage", "Password Incorrect");
    		request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else {
        User user = new User(firstName, lastName, current, save, email, password, base);
        String date = (new java.util.Date()).toString();
        User check = userDao.findUserByMail(email);
        if (check == null) {
        	userDao.saveUser(user);
        	Transaction trans = new Transaction(user.getId(),user.getId(),"INITIAL",date,"SUCCESSFUL",current);
        	transDao.newTransaction(trans);
        	response.sendRedirect(request.getContextPath() + "/login");
        }   
        else {
        	request.setAttribute("errorMessage", "User Already Exists");
    		request.getRequestDispatcher("register.jsp").forward(request, response);
        }	
        }
    }
}
