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

@WebServlet("/withdraw")
public class WithdrawController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
    	HttpSession session = req.getSession(false); 
    	String data = (String)session.getAttribute("id");
    	String f=req.getParameter("fromacc");
    	float amount = Float.parseFloat(req.getParameter("amount"));
    	int flag = 0;
        if (amount <= 0)
        {
        	flag = 0;
        	req.setAttribute("errorMessage", "Please enter a valid amount");
    		req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
        }
        else 
        	flag =1;
        UserDao userDao = UserDaoImplementation.getInstance();
        TransactionDao transDao = TransactionDaoImplementation.getInstance();
		User user = userDao.findUserById(Integer.parseInt(data));
		float current = Float.parseFloat(user.getCurrent());
		float save = Float.parseFloat(user.getSavings());
		String date = (new java.util.Date()).toString();
		if(f.equals("Current") && flag == 1) {
			if(current>amount) {
				user.setCurrent(Float.toString(current-amount));
				userDao.updateUser(user);
				Transaction trans = new Transaction(user.getId(),user.getId(),"WITHDRAW",date,"SUCCESSFUL",Float.toString(amount)+""+user.getCurrency());
	        	transDao.newTransaction(trans);
				
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			else {
				req.setAttribute("errorMessage", "Insufficient balance");
	    		req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
			}
		}
		else if(flag == 1) {
			if(save>amount) {
				user.setSavings(Float.toString(save-amount));
				userDao.updateUser(user);
				Transaction trans = new Transaction(user.getId(),user.getId(),"WITHDRAW",date,"SUCCESSFUL",Float.toString(amount)+""+user.getCurrency());
	        	transDao.newTransaction(trans);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			else {
				req.setAttribute("errorMessage", "Insufficient balance");
	    		req.getRequestDispatcher("withdraw.jsp").forward(req, resp);
			}
		}
	}
	
}
