package social.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.dao.PostDAO;
import social.model.Post;
import social.model.User;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		PostDAO postDAO = new PostDAO();
		RequestDispatcher dispatcher = null;
		
		List<Post> postList = postDAO.getPostRelatedToUser(user.getUser_id());
		request.setAttribute("postList", postList);

		dispatcher = request.getRequestDispatcher("/views/wall.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		PostDAO postDAO = new PostDAO();
		RequestDispatcher dispatcher = null;
		String new_post_text = (String) request.getParameter("post_text");
		String option = (String) request.getParameter("option");

		switch (option) {

			case "post":
			
				Integer user_id = user.getUser_id();
				Date new_date = new Date();

				Post new_post = new Post(0, new_post_text, user_id, new_date);
				postDAO.postOnWall(new_post);

				List<Post> postList = postDAO.getPostRelatedToUser(user.getUser_id());
				request.setAttribute("postList", postList);

				dispatcher = request.getRequestDispatcher("/views/wall.jsp");
				dispatcher.forward(request, response);
				break;

		case "edit":
				String redirect = (String) request.getParameter("redirect");
				Integer post_Id = Integer.valueOf(request.getParameter("post_id"));
				Integer user_Id = user.getUser_id();
				
				if (redirect != null && "true".equals(redirect)) {
					Post editPost = postDAO.getSinglePost(user_Id, post_Id);
					request.setAttribute("editPost", editPost);

					dispatcher = request.getRequestDispatcher("/views/editWall.jsp");
					dispatcher.forward(request, response);
				} else {
					String post_text = (String) request.getParameter("post_text");

					Post oldPost = postDAO.getSinglePost(user_Id, post_Id);
					oldPost.setPost_text(post_text);
					
					postDAO.editPostOnWall(oldPost);

					postList = postDAO.getPostRelatedToUser(user.getUser_id());
					request.setAttribute("postList", postList);
					
					dispatcher = request.getRequestDispatcher("/views/wall.jsp");
					dispatcher.forward(request, response);
				}
				break;

		case "delete":
				String id = request.getParameter("post_id");
				postDAO.deletePostOnWall(Integer.parseInt(id));
				
				postList = postDAO.getPostRelatedToUser(user.getUser_id());
				request.setAttribute("postList", postList);

				dispatcher = request.getRequestDispatcher("/views/wall.jsp");
				dispatcher.forward(request, response);
				break;
		}

	}

}
