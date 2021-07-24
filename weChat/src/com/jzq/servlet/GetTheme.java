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
import com.jzq.model.Sports;
import com.jzq.service.ThemeService;



/**
 * 主题模块，获取运动种类信息
 * @author bill
 * @date   2018年5月3日 上午10:38:43
 * 
 */

@WebServlet("/getTheme")
public class GetTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService = new ThemeService();
	List<Sports> sports = new ArrayList<Sports>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sports = themeService.getThemeList();
		
		//对象转为JSon 保持Date格式
		String themes = JSON.toJSONString(sports);
		
		//返回JSon 给前台		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(themes);
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
