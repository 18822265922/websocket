package com.nkcs.websocket_1.handler;

import java.io.IOException;

/*
 * 如果实现此接口，则需要重写所有函数
 */


import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.nkcs.websocket_1.service.WebSocketService;




public class SystemWebSocketHandler implements WebSocketHandler {

	private static final Log logger = LogFactory.getLog(SystemWebSocketHandler.class);
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	
	@Autowired
	private WebSocketService webSocketService;
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		logger.info("websocket connection closed......");
        users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		logger.info("connect to the websocket success......");
		
        users.add(session);
        String userName = (String) session.getAttributes().get("username");
        if(userName!= null){
            //查询未读消息
            int count = webSocketService.getUnReadNews((String) session.getAttributes().get("username"));
            session.sendMessage(new TextMessage(count + ""));
        }
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		if(session.isOpen()){
            session.close();
        }
        logger.info("websocket connection closed......");
        users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	public WebSocketService getWebSocketService() {
		return webSocketService;
	}

	public void setWebSocketService(WebSocketService webSocketService) {
		this.webSocketService = webSocketService;
	}
	
	public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("username").equals(userName)) {
                try {
                    if (user.isOpen()) {
                    	System.out.println("Sending...");
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
	

}
