<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/websocket_1/websocket/sockjs-1.0.3.min.js"></script>
        <script>
            var websocket;
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://localhost:8080/websocket_1/webSocketServer");
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket("ws://localhost:8080/websocket_1/webSocketServer");
            } else {
                websocket = new SockJS("http://localhost:8080/websocket_1/sockjs/webSocketServer");
            }
            websocket.onopen = function (evnt) {
            };
            websocket.onmessage = function (evnt) {
                $("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
            }
 
        </script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>