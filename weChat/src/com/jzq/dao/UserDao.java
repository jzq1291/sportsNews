package com.jzq.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jzq.model.User;
import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;
import com.jzq.utils.PsSetValues;

/**
 * 用户相关Dao
 * @author bill
 * @date   2018年5月17日 下午2:08:20
 */
public class UserDao {
	static JdbcUtils db = new JdbcUtils();
	
	/**
	 * 判断用户是否存在
	 * @author bill
	 * @date   2018年5月17日 下午2:14:16
	 * @param  openId
	 * @return
	 */
	public boolean checkUser(String openId) {
		User user = new User();
		String sql = "select 1 from user where openId = ?";
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openId);
			}
		}, new IProcessRS() {
			
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				if(rs.next()){
					user.setOpenId(openId);
				}
			}
		});
		if((user.getOpenId())!=null){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 添加用户信息
	 * @author bill
	 * @date   2018年5月17日 下午3:17:59
	 * @param  user
	 */
	public void addUser(User user) {
		String sql = "insert into user(openId,nickName,avatarUrl,country,province,city) "
				+ "values(?,?,?,?,?,?)";
		db.excute_pst(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, user.getOpenId());
				pst.setString(2, user.getNickName());
				pst.setString(3, user.getAvatarUrl());
				pst.setString(4, user.getCountry());
				pst.setString(5, user.getProvince());
				pst.setString(6, user.getCity());
			}
		});
	}
	
	/**
	 * 更新用户信息
	 * @author bill
	 * @date   2018年5月17日 下午3:18:16
	 * @param  user
	 */
	public void updateUser(User user) {
		String sql = "update user set nickName = ? ,avatarUrl = ?,country = ?,province = ?,city = ?"
				+ "where openId = ?";
		
		db.excute_pst(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, user.getNickName());
				pst.setString(2, user.getAvatarUrl());
				pst.setString(3, user.getCountry());
				pst.setString(4, user.getProvince());
				pst.setString(5, user.getCity());
				pst.setString(6, user.getOpenId());
			}
		});
	}

}
