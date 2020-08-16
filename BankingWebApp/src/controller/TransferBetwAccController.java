package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

@WebServlet("/betwAcc")
public class TransferBetwAccController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("transferBetwAcc.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
    	HttpSession session = req.getSession(false); 
    	String data = (String)session.getAttribute("id");
    	String f=req.getParameter("fromacc");
    	String t=req.getParameter("toacc");
    	int flag = 0;
    	float amount = Float.parseFloat(req.getParameter("amount"));
        if (f.equals(t))
        {	
        	flag = 0;
        	req.setAttribute("errorMessage", "Please select different accounts");
    		req.getRequestDispatcher("transferBetwAcc.jsp").forward(req, resp);
        }
        else if (amount <= 0)
        { 
        	flag = 0;
        	req.setAttribute("errorMessage", "Please enter a valid amount");
    		req.getRequestDispatcher("transferBetwAcc.jsp").forward(req, resp);
        }
        else 
        	flag = 1;
        UserDao userDao = UserDaoImplementation.getInstance();
		User user = userDao.findUserById(Integer.parseInt(data));
		TransactionDao transDao = TransactionDaoImplementation.getInstance();
		float current = Float.parseFloat(user.getCurrent());
		float save = Float.parseFloat(user.getSavings());
		String date = (new java.util.Date()).toString();
		if(f.equals("Current") && flag ==1) {
			if(current>amount) {
				user.setCurrent(Float.toString(current-amount));
				user.setSavings(Float.toString(save+amount));
				Transaction trans = new Transaction(user.getId(),user.getId(),"LOCAL_TRANSFER",date,"SUCCESSFUL",Float.toString(amount));
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}
			else {
				req.setAttribute("errorMessage", "Insufficient balance");
	    		req.getRequestDispatcher("home.jsp").forward(req, resp);
			}
		}
		else if (flag ==1) {
			if(save>amount) {
				user.setCurrent(Float.toString(current+amount));
				user.setSavings(Float.toString(save-amount));
				Transaction trans = new Transaction(user.getId(),user.getId(),"LOCAL_TRANSFER",date,"SUCCESSFUL",Float.toString(amount));
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
			}
			else {
				req.setAttribute("errorMessage", "Insufficient balance");
	    		req.getRequestDispatcher("home.jsp").forward(req, resp);
			}
		}
	}
}
