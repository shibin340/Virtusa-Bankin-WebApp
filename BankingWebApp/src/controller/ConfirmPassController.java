package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;
@WebServlet("/confirmPassword")
public class ConfirmPassController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("confirmPass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao userDao = UserDaoImplementation.getInstance();
		HttpSession session = req.getSession(false);
		String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        User user = (User)session.getAttribute("userforgot");
        if(password.length()<6) {
    		req.setAttribute("errorMessage", "Password too short.");
    		req.getRequestDispatcher("confirmPass.jsp").forward(req, resp);
    	}
        else if(!cpassword.equals(password)) {
        	req.setAttribute("errorMessage", "Password Incorrect");
    		req.getRequestDispatcher("confirmPass.jsp").forward(req, resp);
        }
        else {
        	user.setPassword(cpassword);
        	userDao.updatePassword(user);
        	session.setAttribute("method", "");
        	resp.sendRedirect(req.getContextPath()+"/login");
        }
	}
	
}
