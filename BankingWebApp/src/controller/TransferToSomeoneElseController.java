package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import contents.ForexDao;
import contents.ForexDaoImplementation;
import contents.Transaction;
import contents.TransactionDao;
import contents.TransactionDaoImplementation;
import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;

@WebServlet("/toSomeoneElse")
public class TransferToSomeoneElseController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
    	HttpSession session = req.getSession(false); 
    	String data = (String)session.getAttribute("id");
    	String t=req.getParameter("fromacc");
    	int recId = Integer.parseInt(req.getParameter("recId"));
    	String recMail = req.getParameter("recEmail");
    	String recFullname = req.getParameter("recFullname");
    	float u_amount = Float.parseFloat(req.getParameter("amount"));
    	float r_amount = u_amount;
    	int flag = 0;
        if (u_amount <= 0)
        {
        	flag = 0;
        	req.setAttribute("errorMessage", "Please enter a valid amount");
    		req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
        }
        else 
        	flag = 1;
        UserDao userDao = UserDaoImplementation.getInstance();
		User user = userDao.findUserById(Integer.parseInt(data));
		float current = Float.parseFloat(user.getCurrent());
		float save = Float.parseFloat(user.getSavings());
		TransactionDao transDao = TransactionDaoImplementation.getInstance();
		String date = (new java.util.Date()).toString();
		ResultSet resultSet = null;
		try
    	{
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "5566");
    	Statement st=conn.createStatement();
    	resultSet = st.executeQuery("select * from auth_users where idauth_users = '"+recId+"' && auth_email = '"+recMail+"'");
    	if(resultSet.next() && flag == 1){
    		if(t.equals("Current")) {
    	    	if(u_amount>current) {
    	    		req.setAttribute("errorMessage", "Insufficient Balance.");
    	    		req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
    	    	}
				/*user.setCurrent(Float.toString(current-amount));
				recipient.setCurrent(Float.toString(recurrent+amount));
				Transaction trans = new Transaction(recipient.getId(),user.getId(),"TRANSFER",date,"SUCCESSFUL",Float.toString(amount));
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				userDao.updateUser(recipient);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");*/
    	    }
    	    else {
    	    	if(u_amount>save) {
    	    		req.setAttribute("errorMessage", "Insufficient Balance.");
    	    		req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
    	    	}
				/*user.setCurrent(Float.toString(save-amount));
				recipient.setCurrent(Float.toString(recsave+amount));
				Transaction trans = new Transaction(recipient.getId(),user.getId(),"TRANSFER",date,"SUCCESSFUL",Float.toString(amount));
	        	transDao.newTransaction(trans);
				userDao.updateUser(user);
				userDao.updateUser(recipient);
				//req.getRequestDispatcher("home.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/home");*/
    	    } 
    		User recipient = userDao.findUserById(recId);
    		String base = user.getCurrency();
    		String target = recipient.getCurrency();
    		if(!base.equals(target)) {
    			ForexDao forexDao = ForexDaoImplementation.getInstance();
    			String mult = forexDao.getMultiplier(base, target);
    			float multiplier = Float.parseFloat(mult);
    			r_amount = r_amount * multiplier;
    		}
    		float recurrent = Float.parseFloat(recipient.getCurrent());
    		float recsave = Float.parseFloat(recipient.getSavings());
    		Random rand = new Random();
    		int otp = rand.nextInt(900000) + 100000;
    		session.setAttribute("otp",otp);
    		session.setAttribute("from", t);
    		session.setAttribute("amount", u_amount);
    		session.setAttribute("r_amount", r_amount);
    		session.setAttribute("user", user);
    		session.setAttribute("rec", recipient);
    		
    		String to = user.getEmail();

    		// Sender's email ID needs to be mentioned
    		String from = "shibin340@gmail.com";

    		// Assuming you are sending email from localhost
    		String host = "smtp.gmail.com";

    		// Get system properties
    		Properties properties = System.getProperties();

    		// Setup mail server
    		properties.setProperty("mail.smtp.host", host);
    		properties.setProperty("mail.transport.protocol", "smtp");
    		properties.setProperty("mail.smtp.auth", "true");
    		properties.setProperty("mail.smtp.starttls.enable", "true");
    		properties.setProperty("mail.user", from);
    		properties.setProperty("mail.password", "YOUR_PASS");
    		properties.setProperty("mail.port", "465");

    		// Get the default Session object.
    		Session session2 = Session.getInstance(properties, new javax.mail.Authenticator(){
    			@Override
    			protected PasswordAuthentication getPasswordAuthentication(){
    				return new PasswordAuthentication(from,"YOUR_PASS");
    			}
    		});

    		try{
    		   // Create a default MimeMessage object.
    		   MimeMessage message = new MimeMessage(session2);

    		   // Set From: header field of the header.
    		   message.setFrom(new InternetAddress(from));

    		   // Set To: header field of the header.
    		   message.addRecipient(Message.RecipientType.TO,
    		                            new InternetAddress(to));

    		   // Set Subject: header field
    		   message.setSubject("Verification code for completion of your transfer");

    		   // Now set the actual message
    		   message.setText("Your one time otp for transfer of "+u_amount+" to "+recFullname+" is : "+otp+"\n\n If not made by you, contact your nearest branch and block your account.");

    		   // Send message
    		   Transport.send(message);
    		   System.out.println("OTP message sent successfully....");
    		   resp.sendRedirect(req.getContextPath() + "/VerifyOTP");
    		}catch (MessagingException mex) {
    		   mex.printStackTrace();
    		}
    	}
    	else {
    		req.setAttribute("errorMessage", "Invalid userId or email");
    		req.getRequestDispatcher("transferToSomeoneElse.jsp").forward(req, resp);
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.print(e);
    		e.printStackTrace();
    	}
	}

}
