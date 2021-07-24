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
import com.jzq.model.ListItem;
import com.jzq.service.NewsService;


/**
 * 查询新闻列表
 * @author bill
 * @date   2018年4月16日 下午4:53:48
 * 返回list<ListItem> 到前台
 */

@WebServlet("/getNewsList")
public class GetNewsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @author bill
	 * @date   2018年4月16日 下午5:03:44
	 * 查询列表的条件 1:推荐的新闻(最热/最新)、按运动类型查找
	 */
	private String way;
	private String openId;
	private Integer sport_id;
	private NewsService service = new NewsService();
	private List<ListItem> newsList = new ArrayList<ListItem>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		获取查询方式
		way = request.getParameter("way");
//		根据用户喜好推荐
		if("recommend".equals(way)){
			openId = request.getParameter("openId");
			newsList = service.getNewsListByRecommend(openId);
		}
		//按体育项目种类
		else if("category".equals(way)){
			Integer page = Integer.parseInt(request.getParameter("page"));
			sport_id = Integer.parseInt(request.getParameter("sport_id"));
			newsList = service.getNewsListByCategory(sport_id,page);
		}
//		历史记录
		else if("history".equals(way)){
			openId = request.getParameter("openId");
			newsList = service.getNewsListByHistory(openId);
		}
//		新闻搜索
		else if("search".equals(way)){
			String title = new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8");
			newsList = service.getNewsListByTitle(title);
		}
		
		
		//List 转为Json		
		String newsData = JSON.toJSONString(newsList);
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
