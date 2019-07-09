package social.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import social.dao.FriendsDAO;
import social.dao.RequestDAO;
import social.model.Friends;
import social.model.User;

/**
 * Servlet implementation class RequestFriendship
 */
@WebServlet("/UpdateRequest")
public class UpdateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRequest() {
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
		
		int requestId = Integer.valueOf(request.getParameter("requestId"));
		int reply = Integer.valueOf(request.getParameter("reply"));
		int sourceId = Integer.valueOf(request.getParameter("sourceId"));
		int targetId = Integer.valueOf(request.getParameter("targetId"));
		
		RequestDAO rDao = new RequestDAO();
		FriendsDAO fDao = new FriendsDAO();
		
		Friends newFriend = new Friends();
		User tempUser = new User();
		tempUser.setUser_id(targetId);
		
		newFriend.setUser_request(sourceId);
		newFriend.setUser_accepted(tempUser);
		newFriend.setRequest_id(requestId);
		
		// Accept and Confirm friendship
		rDao.requestUpdate(requestId, reply);
		fDao.confirmFriendShip(newFriend);
		
		response.sendRedirect("views/notification.jsp");
		
		System.out.println("[POST] UpdateRequest: " + requestId + ", " + reply);
	}

}
