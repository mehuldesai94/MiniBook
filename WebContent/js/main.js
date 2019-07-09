
function sendFriendRequest(from, to) {

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SocialMediaNetworkAssignment/FriendRequest',
        data: { 'from': from, 'to': to }
    })
        .done(function(data) {
        	console.log("Success: Send a friend request");
        	
        	window.location.href = "friends.jsp";
        })
        .fail(function(err) {
        	console.log("Log: Failed to retrieve department data");
            //$('#data').html("<span style='color: red'>Failed to retrieve department data</span>");
        });
}

function acceptOrDeclineRequest(sourceId, targetId, requestId, reply) {

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SocialMediaNetworkAssignment/UpdateRequest',
        data: { 'sourceId': sourceId, 'targetId': targetId, 'requestId': requestId, 'reply': reply }
    })
        .done(function(data) {
        	console.log("Success: Friend request was accepted/declined.");
        	
        	window.location.href = "notification.jsp";
        })
        .fail(function(err) {
        	console.log("Log: Failed to update friend request");
        });
}

$(document).ready(function() {
    console.log("jQuery is ready!");
});