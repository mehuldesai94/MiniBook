package social.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import social.dao.FriendsDAO;
import social.dao.RequestDAO;

/**
 * Servlet implementation class FriendRequest
 */
@WebServlet("/FriendRequest")
public class FriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String fromId = request.getParameter("from");
		String toId = request.getParameter("to");
		int requestToFriend = Integer.parseInt(toId);
		int fromUserId = Integer.parseInt(fromId);
		
		RequestDAO rDAO = new RequestDAO();
		FriendsDAO fDao = new FriendsDAO();
		
		rDAO.requestFriendShip(fromUserId, requestToFriend);
		
//		RequestDAO rDao = new RequestDAO();
//		rDao.requestFriendShip(Integer.valueOf(fromId), Integer.valueOf(toId));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/friends.jsp");
		dispatcher.forward(request, response);
		//response.sendRedirect("views/friends.jsp");
		
		System.out.println("[POST] FriendRequest: " + fromId + ", " + toId);
	}

}
