package controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/deposit")
public class DepositController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("deposit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
    	HttpSession session = req.getSession(false); 
    	String data = (String)session.getAttribute("id");
    	String t=req.getParameter("toacc");
    	float amount = Float.parseFloat(req.getParameter("amount"));
    	int flag = 0;
        if (amount <= 0)
        {
        	flag = 0;
        	req.setAttribute("errorMessage", "Please enter a valid amount");
    		req.getRequestDispatcher("deposit.jsp").forward(req, resp);
        }
        else
        	flag = 1;
        UserDao userDao = UserDaoImplementation.getInstance();
        TransactionDao transDao = TransactionDaoImplementation.getInstance();
		User user = userDao.findUserById(Integer.parseInt(data));
		float current = Float.parseFloat(user.getCurrent());
		float save = Float.parseFloat(user.getSavings());
		String date = (new java.util.Date()).toString();
		String currency = user.getCurrency();
		if(t.equals("Current") && flag == 1) {
				user.setCurrent(Float.toString(current+amount));
				userDao.updateUser(user);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				Transaction trans = new Transaction(user.getId(),user.getId(),"DEPOSIT",date,"SUCCESSFUL",Float.toString(amount)+""+currency);
	        	transDao.newTransaction(trans);
				
				resp.sendRedirect(req.getContextPath() + "/home");
		}
		else {
			if(flag==1)
			{user.setSavings(Float.toString(save+amount));
			userDao.updateUser(user);
			//req.getRequestDispatcher("home.jsp").forward(req, resp);
			Transaction trans = new Transaction(user.getId(),user.getId(),"DEPOSIT",date,"SUCCESSFUL",Float.toString(amount)+""+currency);
        	transDao.newTransaction(trans);
			
			resp.sendRedirect(req.getContextPath() + "/home");}
		}
	}

}
