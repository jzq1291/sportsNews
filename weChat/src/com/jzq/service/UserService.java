package com.jzq.service;

import org.junit.Test;

import com.jzq.dao.UserDao;
import com.jzq.model.User;

/**
 * 用户相关业务
 * @author bill
 * @date   2018年5月17日 下午2:06:13
 */
public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * 登录，将用户信息存入数据库或更新用户信息
	 * @author bill
	 * @date   2018年5月17日 下午2:08:47
	 * @param user
	 * @return
	 */
	public String login(User user) {
		String openId = user.getOpenId();
		boolean flag = userDao.checkUser(openId);
		if(flag){
//			存在，更新用户数据
			System.out.println("用户存在");
			userDao.updateUser(user);
		}else {
//			不存在,添加新用户
			System.out.println("用户不存在");
			userDao.addUser(user);
		}
		return null;
	}
	
	
	@Test
	public void test(){
		String openId = "111";
		boolean flag = userDao.checkUser(openId);
		if(flag){
			System.out.println("用户存在");
//			存在，更新用户数据，查询用户喜好
		}else {
//			不存在,添加新用户
			System.out.println("用户不存在");
		}
	}


	public String getOpenid(String appid, String secret, String js_code) {
		return "oAtb10JR17ionlVmXUZIwVqwcxDg";
	}

}
