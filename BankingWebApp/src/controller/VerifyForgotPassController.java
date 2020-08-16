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

@WebServlet("/verifyPassword")
public class VerifyForgotPassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("otpVerifyPassword.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		User user = (User)session.getAttribute("userforgot");
		int otp = (int)session.getAttribute("potp");
		int enteredotp = Integer.parseInt(req.getParameter("enteredOtp"));
		if(otp == enteredotp) {
			resp.sendRedirect(req.getContextPath()+"/confirmPassword");
		}
		else {
			req.setAttribute("errorMessage", "Incorrect OTP");
			req.getRequestDispatcher("otpVerifyPassword.jsp").forward(req, resp);
		}
	}
	
}
