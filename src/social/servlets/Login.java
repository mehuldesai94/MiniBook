package social.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.dao.PostDAO;
import social.dao.UserDAO;
import social.model.Post;
import social.model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");

		UserDAO userDAO = new UserDAO();
		User user = new User();

		try {
			user = userDAO.getUserByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user != null && password.equals(user.getPass())) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			PostDAO postDAO = new PostDAO();
			List<Post> postList = postDAO.getPostRelatedToUser(user.getUser_id());
			request.setAttribute("postList", postList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/wall.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
			request.setAttribute("message",	"Username and password do not match");
			dispatcher.forward(request, response);
		}

	}

}
