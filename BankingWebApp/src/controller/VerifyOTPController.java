package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.Transaction;
import contents.TransactionDao;
import contents.TransactionDaoImplementation;
import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;

@WebServlet("/VerifyOTP")
public class VerifyOTPController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("otpVerify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		int otp = (int)session.getAttribute("otp");
		float amount = (float)session.getAttribute("amount");
		float r_amount = (float)session.getAttribute("r_amount");
		User user = (User)session.getAttribute("user");
		User rec = (User)session.getAttribute("rec");
		String from = (String)session.getAttribute("from");
		int enteredotp = Integer.parseInt(req.getParameter("enteredotp"));
		float recurrent = Float.parseFloat(rec.getCurrent());
		float recsave = Float.parseFloat(rec.getSavings());
		float current = Float.parseFloat(user.getCurrent());
		float save = Float.parseFloat(user.getSavings());
		UserDao userDao = UserDaoImplementation.getInstance();
		TransactionDao transDao = TransactionDaoImplementation.getInstance();
		String date = (new java.util.Date()).toString();
		if(otp == enteredotp) {
			if(from.equals("Current")) {
				user.setCurrent(Float.toString(current-amount));
				rec.setCurrent(Float.toString(recurrent+r_amount));
				Transaction trans = new Transaction(rec.getId(),user.getId(),"TRANSFER",date,"SUCCESSFUL",Float.toString(amount)+""+user.getCurrency());
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				userDao.updateUser(rec);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			else {
				user.setSavings(Float.toString(save-amount));
				rec.setSavings(Float.toString(recsave+r_amount));
				Transaction trans = new Transaction(rec.getId(),user.getId(),"TRANSFER",date,"SUCCESSFUL",Float.toString(amount)+""+user.getCurrency());
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				userDao.updateUser(rec);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		}
		else {
	    		req.setAttribute("errorMessage", "Incorrect OTP");
	    		//req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
	    		resp.sendRedirect(req.getContextPath() + "/toSomeoneElse");
		}
	}

}
