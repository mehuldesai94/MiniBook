package social.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.dao.FriendsDAO;
import social.model.Friends;

/**
 * Servlet implementation class MyFriendServlet
 */
@WebServlet("/MyFriendsServlet")
public class MyFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int userId = Integer.valueOf(request.getParameter("userid"));
		String friendType = request.getParameter("friends");
		HttpSession session = request.getSession();
		
		FriendsDAO fDao = new FriendsDAO();
		
		session.removeAttribute("myfriends");
		session.removeAttribute("mynonfriends");
		
		if (friendType.equals("myfriends")) {
			List<Friends> friendsList = fDao.getAllUserFriends(userId);
			session.setAttribute("myfriends", friendsList);
			
			
			response.sendRedirect("views/friends.jsp");
		}
		else if (friendType.contentEquals("findfriends")){
			List<Friends> friendsList = fDao.getAllNonFriends(userId);
			session.setAttribute("mynonfriends", friendsList);
			
			
			response.sendRedirect("views/friends.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
