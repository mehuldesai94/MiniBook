<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="social.model.*" import="java.util.*"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Wall</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<%
	User user = (User) session.getAttribute("user");
	List<Post> postList = (List<Post>) request.getAttribute("postList");
	System.out.println("total posts: " + postList.size() + " user id: " + user.getUser_id());
%>
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
* {box-sizing: border-box;}
/* Button used to open the chat form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

/* The popup chat - hidden by default */
.chat-popup {
 max-width: 300px;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width textarea */
  .form-container textarea {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    border: none;
    background: #f1f1f1;
    resize: none;
    min-height: 200px;
  }

/* When the textarea gets focus, do something */
.form-container textarea:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/send button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}


/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}

div.postMessage {
	width: 50%;
	background-color: white;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	margin-bottom: 25px;
}
</style>
<body>
	<ul>
		<li style="float: left"><a>MiniBook</a></li>
		<li style="float: right"><a href="Logout">Log Out</a></li>
		<li style="float: right"><a href="views/notification.jsp">Notification</a></li>
		<li style="float: right"><a href="views/friends.jsp">Friends</a></li>
		<li style="float: right"><a class="active" href="wall.jsp">Wall</a></li>
	</ul>

	<br />
	<br />

	<!-- This table for display basic information name end image profile -->
	<h1>Profile</h1>

	<!-- <table style="width: 100%">
		<tr>
			<td><img src=<%--<%="images/" + user.getImg()%>--%> alt="HTML5 Icon"
				style="width: 100px; height: 100px;"></td>
		</tr>
		<tr>
			<td><%--<%=user.getFirst_name() + " " + user.getLast_name()%>--%></td>
		</tr>
	</table> -->



	<div class="polaroid">
		<img src=<%="images/" + user.getImg()%> alt="5 Terre"
			style="width: 25%; height: 25%;">
		<div class="container">
			<p><%=user.getFirst_name() + " " + user.getLast_name()%></p>
		</div>
	</div>



	<!-- This is table for Post  -->
	<div class="chat-popup" id="myForm">
		<form action="PostServlet" method="post" class="form-container">
			<label for="msg"><h3>New Post</h3></label>
			<textarea placeholder="Type message.." name="post_text" required></textarea>
			<button type="submit" class="btn" name="option" value="post">Post</button>
		</form>
	</div>


	<h1>My posts</h1>
	<%
		if (postList.size() > 0) {
			System.out.println("total posts: " + postList.size() + " user id: " + user.getUser_id());
			
			String img = "<img src='images/" + user.getImg() + "'" + "alt='HTML5 Icon'"
					+ "style='width:100px;height:100px;'";

			for (Post i : postList) {
				out.print("<div class='postMessage'>");
				out.print("<form action='PostServlet' method='post'>");
				out.print("<table style='width:50%;'");
				out.print("<tr>");
				out.print("<td width='120px' height='120px'>" + img + "</td>");
				out.print("<td colspan='2' style='background-color:AliceBlue;border-radius: 25px; width:50%;'>"
						+ i.getPost_text() + "</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>" + user.getFirst_name() + " " + user.getLast_name() + "</td>");
				out.print("<input type='hidden' name='post_id' value='" + i.getPost_id() + "'/>");
				out.print("<input type='hidden' name='redirect' value='true'/>");
				out.print("<td><input type='submit' name='option' value='edit'/>");
				out.print("<input type='submit' name='option' value='delete'/></td>");
				out.print("</tr>");
				out.print("</table>");
				out.print("</form>");
				out.print("</div>");
			}
			
		}
	%>

</body>
</html>