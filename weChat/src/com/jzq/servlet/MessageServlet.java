package com.jzq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jzq.model.Message;
import com.jzq.service.MessageService;

@WebServlet("/messageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String way;
	private MessageService messageService = new MessageService();
	private List<Message> messageList = new ArrayList<Message>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		way = request.getParameter("way");
//		添加留言
		if("addmessage".equals(way)){
			String openId = request.getParameter("openid");
			String email = request.getParameter("email");
			String content = new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8");
			if(email.equals("")||content.equals("")){
				return ;
			}
			Message message = new Message(openId, content, email);
			messageService.addMessage(message);
		}else if ("getmessage".equals(way)) {
			String openId = request.getParameter("openid");
			messageList = messageService.getMessage(openId);
			//List 转为Json		
			String messageData = JSON.toJSONStringWithDateFormat(messageList,"yyyy-MM-dd");
			//返回JSon 给前台		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			try {
			    out = response.getWriter();
			    out.write(messageData);
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (out != null) {
			        out.close();
			    }
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
