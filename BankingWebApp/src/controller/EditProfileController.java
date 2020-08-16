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

@WebServlet("/editProfile")
public class EditProfileController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("editProfile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("newfname");
    	String lname = req.getParameter("newlname");
    	String mail = req.getParameter("newmail");
    	HttpSession session = req.getSession(false); 
    	String data = (String)session.getAttribute("id");
    	UserDao userDao = UserDaoImplementation.getInstance();
		User user = userDao.findUserById(Integer.parseInt(data));
		user.setFirstname(fname);
		user.setLastname(lname);
		user.setEmail(mail);
		userDao.updateProfile(user);
		resp.sendRedirect(req.getContextPath() + "/profile");
	}
}
