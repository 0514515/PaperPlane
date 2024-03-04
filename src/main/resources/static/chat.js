var stompClient = null;
var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/'+subscribeId, function (greeting) {
        showGreeting(JSON.parse(greeting.body).message);
    });
});

//----Current Time----
var today = new Date();

var year = today.getFullYear();
var month = ('0' + (today.getMonth() + 1)).slice(-2);
var day = ('0' + today.getDate()).slice(-2);

var dateString = year + '-' + month  + '-' + day;

var hours = ('0' + today.getHours()).slice(-2);
var minutes = ('0' + today.getMinutes()).slice(-2);
var seconds = ('0' + today.getSeconds()).slice(-2);

var timeString = hours + ':' + minutes  + ':' + seconds;

var currentTime = dateString + ' ' + timeString
//--------------------

function sendMessage() {
    stompClient.send("/app/hello", {}, JSON.stringify({ 'from' : userName,
                                                        'message': $("#message").val(),
                                                        'time': currentTime,
                                                        'subscribeId': subscribeId,
                                                        'type' : "message",
                                                        'subscribe' : subscribeName
                                                        }));
    $("#message").val('');
    $("#message").html("");
}

function sendInvite(){
    stompClient.send("/app/hello", {}, JSON.stringify({ 'from' : userName,
                                                        'message': $("#invite").val(),
                                                        'time': currentTime,
                                                        'subscribeId': subscribeId,
                                                        'type' : "invite",
                                                        'subscribe' : subscribeName
                                                        }));
    $("#invite").val('');
    $("#invite").html("");
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + " " + "[" + currentTime + "]" + "</td></tr>");
    $("#table").scrollTop($("table")[0].scrollHeight);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendMessage(); });
    $( "#send1" ).click(function() { sendInvite(); });
});