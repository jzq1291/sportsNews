package com.jzq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.jzq.model.News;
import com.jzq.service.NewsService;


/**
 * 通过前台传回的news_id 返回新闻内容（Json）
 * @author bill
 * @date   2018年4月16日 下午4:45:29
 */
@WebServlet("/getNews")
public class GetNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取前台参数news_id		
		Integer news_id = Integer.parseInt(request.getParameter("news_id"));
		String openid = request.getParameter("openid");
		NewsService service = new NewsService();
		News news = service.getNewsById(news_id,openid);
		
		//对象转为JSon 保持Date格式
		String newsData = JSON.toJSONStringWithDateFormat(news,"yyyy-MM-dd HH:mm:ss");
		
		//返回JSon 给前台		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(newsData);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
