<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="social.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Wall</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<style type="text/css">

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

</style>
</head>
<body>

	<%
		User user = (User) session.getAttribute("user");
		Post post = (Post) request.getAttribute("editPost");
	%>

	<ul>
		<li style="float: left"><a>MiniBook</a></li>
		<li style="float: right"><a href="/Logout">Log Out</a></li>
		<li style="float: right"><a href="views/notification.jsp">Notification</a></li>
		<li style="float: right"><a href="views/friends.jsp">Friends</a></li>
		<li style="float: right"><a href="wall.jsp">Wall</a></li>
	</ul>

	<br />
	<br />



	<div class="polaroid">
		<img src=<%="images/" + user.getImg()%> alt="5 Terre"
			style="width: 25%; height: 25%;">
		<div class="container">
			<p><%=user.getFirst_name() + " " + user.getLast_name()%></p>
		</div>
	</div>

	<div class="chat-popup" id="myForm">
		<form aaction="../PostServlet" method="post" class="form-container">
			<input type="hidden" name="post_id" value="<%=post.getPost_id()%>" />
			<label for="msg"><h3>Edit Post</h3></label>
			<textarea placeholder="Type message.." name="post_text" required><%=post.getPost_text()%></textarea>
			<button type="submit" class="btn" name="option" value="edit">Edit</button>
		</form>
	</div>
</body>
</html>