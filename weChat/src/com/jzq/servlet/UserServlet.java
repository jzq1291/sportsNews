package com.jzq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzq.model.User;
import com.jzq.service.UserService;

/**
 * 用户信息相关Servlet
 * 
 * @author bill
 * @date 2018年5月12日 上午12:14:44
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String appid = "wx890a5a4d5b3c142e";
	private static final String secret = "9b90d7b288668af05f3028e588612291"; 
	private String way;
	private UserService userService = new UserService();

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		way = request.getParameter("way");

		if ("login".equals(way)) { // 登录
			String openId = "";
		// 获取用户信息
			String userInfo = new String(request.getParameter("userInfo")
					.getBytes("iso-8859-1"), "utf-8");
			JSONObject jsonuser = JSON.parseObject(userInfo);
			String keyUserinfo = request.getParameter("keyUserinfo");
			JSONObject jsonuserKey = JSON.parseObject(keyUserinfo);
			
			if(jsonuserKey !=null){
				openId = jsonuserKey.getString("openid");
			}
			String nickName = jsonuser.getString("nickName");
			String avatarUrl = jsonuser.getString("avatarUrl");
			String country = jsonuser.getString("country");
			String province = jsonuser.getString("province");
			String city = jsonuser.getString("city");
			
			User user = new User(openId, nickName, avatarUrl, country, province, city);
			
			String proFile = userService.login(user);			
			
		}else if("getOpenid".equals(way)){
			String js_code = request.getParameter("js_code");
			String openid = userService.getOpenid(appid,secret,js_code);
			System.out.println(openid);
		} 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
