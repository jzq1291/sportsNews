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
import com.jzq.model.SportsItem;
import com.jzq.service.ProfileService;

/**
 * 用户喜好
 * @author bill
 * @date   2018年6月1日 下午4:41:43
 */
@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String way;
	private String openId;
	private ProfileService profileService = new ProfileService();
       
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		way = request.getParameter("way");
		openId = request.getParameter("openid");
//		获取用户喜好
		if ("getProfile".equals(way)){
			List<SportsItem> profile = profileService.getProfile(openId);
			
			//对象转为JSon 保持Date格式
			String themes = JSON.toJSONString(profile);
			
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
		}else if("updateProfile".equals(way)){//更新用户喜好
			List<String> pros = new ArrayList<String>();
			String profiels = request.getParameter("profiles");
			if("".equals(profiels)){
			}else {
				pros = java.util.Arrays.asList(profiels.split(","));
			}
			profileService.updateProfile(openId,pros);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
