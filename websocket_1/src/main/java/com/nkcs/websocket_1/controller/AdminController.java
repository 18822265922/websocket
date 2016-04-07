package com.nkcs.websocket_1.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.nkcs.websocket_1.handler.SystemWebSocketHandler;

@Controller
public class AdminController {

	static Log logger = LogFactory.getLog(AdminController.class);
	 
    //@Autowired(required = false)
    //private AdminService adminService;
 
    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
 
 
    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request){
        //无关代码都省略了
    	String username = "YF";
        int unReadNewsCount = 1;
        systemWebSocketHandler().sendMessageToUser(username, new TextMessage(unReadNewsCount + ""));
        return "result";
    }
}
