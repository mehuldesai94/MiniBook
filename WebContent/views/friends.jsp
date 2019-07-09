<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="social.dao.*"%>
<%@ page import="social.model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Friends</title>
<style>
body {
	margin: 25px;
	background-color: #D3D3D3;
}

div.polaroid {
	width: 80%;
	background-color: white;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	margin-bottom: 25px;
}

div.container {
	text-align: center;
	padding: 10px 20px;
}

img {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li


 


a




:hover




:not


 


(
.active


 


)
{
background-color




:


 


#111




;
}
.active {
	background-color: #4CAF50;
}

/* The container */
.container {
	position: relative;
	padding-left: 35px;
	margin-bottom: 12px;
	cursor: pointer;
	font-size: 18px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* Hide the browser's default radio button */
.container input {
	position: absolute;
	opacity: 0;
	cursor: pointer;
}

/* Create a custom radio button */
.checkmark {
	position: absolute;
	top: 0;
	left: 0;
	height: 25px;
	width: 25px;
	background-color: #eee;
	border-radius: 50%;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
	background-color: #ccc;
}

/* When the radio button is checked, add a blue background */
.container input:checked ~ .checkmark {
	background-color: #2196F3;
}

/* Create the indicator (the dot/circle - hidden when not checked) */
.checkmark:after {
	content: "";
	position: absolute;
	display: none;
}

/* Show the indicator (dot/circle) when checked */
.container input:checked ~ .checkmark:after {
	display: block;
}

/* Style the indicator (dot/circle) */
.container .checkmark:after {
	top: 9px;
	left: 9px;
	width: 8px;
	height: 8px;
	border-radius: 50%;
	background: white;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 10px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 20%;
}

button:hover {
	opacity: 0.8;
}
</style>
</head>
<body>
	<ul>
		<li style="float: left"><a>MiniBook</a></li>
		<li style="float: right"><a href="../Logout">Log Out</a></li>
		<li style="float: right"><a href="notification.jsp">Notification</a></li>
		<li style="float: right"><a class="active" href="friends.jsp">Friends</a></li>
		<li style="float: right"><a href="../PostServlet">Wall</a></li>
	</ul>

	<%
		User user = (User) session.getAttribute("user");
		UserDAO uDao = new UserDAO();
		// Retrieve from server context?
		// User user = new User();
		//user = uDao.getUserByEmail("bvasquez@gmail.com");
		int userId = user.getUser_id();
	%>
	<h1>Profile</h1>
	<div class="polaroid">
		<img src=<%="../images/" + user.getImg()%> alt="5 Terre"
			style="width: 25%; height: 25%;">
		<div class="container">
			<p><%=user.getFirst_name() + " " + user.getLast_name()%></p>
		</div>
	</div>
	<br />

	<form method="get" action="../MyFriendsServlet"
		style="text-align: center;">
		<div class="polaroid">
			<h2>Search Friends</h2>
			<label class="container">My Friends <input type="radio"
				name="friends" value="myfriends"> <span class="checkmark"></span>
			</label> <label class="container">Find Friends <input type="radio"
				name="friends" value="findfriends" checked="checked"> <span class="checkmark"></span>
			</label> <input type="hidden" name="userid" value="<%=userId%>" />
			<button type="submit" name="search" value="search">Search</button>
		</div>
	</form>

	<br>
	<table style="text-align: center;">

		<%
			List<Friends> myFriends = (List<Friends>) session.getAttribute("myfriends");
			List<Friends> myNonFriends = (List<Friends>) session.getAttribute("mynonfriends");
			if (myFriends != null) {
				out.print("<h3> My friends: " + myFriends.size() +"</h3>");
				for (int i = 0; i < myFriends.size(); i++) {
					User friend = myFriends.get(i).getUser_accepted();
					out.print("<div class='polaroid'><tr><td>" + "<img src='../images/img" + friend.getUser_id()
					+ ".png' style='width:10%; height:auto; border:solid;'/>" + "<br><div class='container'>" + "Name: "
					+ friend.getFirst_name() + " " + friend.getLast_name() + "<br>" + "About me: "
					+ friend.getAboutMe() + "<br />");
				}
			}
			if (myNonFriends != null) {
				RequestDAO rDao = new RequestDAO();
				out.print("<h3> Friends Suggestions: " + myNonFriends.size() + "</h3><br><br>");
				for (int i = 0; i < myNonFriends.size(); i++) {
					User friend = myNonFriends.get(i).getUser_accepted();
					boolean requestExists = rDao.checkRequest(userId, friend.getUser_id());

					out.print("<div class='polaroid'><tr><td>" + "<img src='../images/img" + friend.getUser_id()
							+ ".png' style='width:10%; height:auto; border:solid;'/>" + "<br><div class='container'>" + "Name: "
							+ friend.getFirst_name() + " " + friend.getLast_name() + "<br>" + "About me: "
							+ friend.getAboutMe() + "<br />");

					if (!requestExists) {
						out.print("<button onclick='sendFriendRequest(" + userId + ", " + friend.getUser_id()
								+ ")'>Friend Request</button>");
					}

					out.print("<br><br>" + "</td></tr></div></div>");
				}
			}
		%>
	</table>

	<!-- Latest compiled and minified jQuery 3.2.1 JavaScript -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>

	<!-- custom JavaScript -->
	<script src="../js/main.js"></script>
</body>
</html>