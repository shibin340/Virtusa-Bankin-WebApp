package controller;

import java.io.IOException;
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

import contents.User;
import contents.UserDao;
import contents.UserDaoImplementation;

@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("forgotPass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = req.getParameter("forgotmail");
		UserDao userDao = UserDaoImplementation.getInstance();
		User user = userDao.findUserByMail(mail);
		if(user == null) {
			req.setAttribute("errorMessage", "No user exists, Please register first.");
			req.getRequestDispatcher("forgotPass.jsp").forward(req, resp);
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("userforgot", user);
			Random rand = new Random();
    		int otp = rand.nextInt(900000) + 100000;
    		session.setAttribute("potp",otp);
    		
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
    		   message.setSubject("Verification code to reset your password");

    		   // Now set the actual message
    		   message.setText("Your one time otp for resetting your password is : "+otp+"\n\n If not made by you, Please don't share this otp with anyone else.");

    		   // Send message
    		   Transport.send(message);
    		   System.out.println("OTP message sent successfully....");
    		   resp.sendRedirect(req.getContextPath() + "/verifyPassword");
    		   //req.getRequestDispatcher("otpVerifyPassword.jsp").forward(req, resp);
    		}catch (MessagingException mex) {
    		   mex.printStackTrace();
    		}
		}
	}

}
