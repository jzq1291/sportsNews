package com.jzq.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzq.model.Profile;
import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;
import com.jzq.utils.PsSetValues;

public class ProfileDao {
	static JdbcUtils db = new JdbcUtils();

	
	/**
	 * 通过openId返回用户喜好
	 * 
	 * @author bill
	 * @date   2018年5月7日 下午7:09:50
	 * @param  openId
	 * @return Profile
	 */
	public Profile getProfile(String openId) {
		Profile profile = new Profile();
		String sql = "select openId,pro1,pro2,pro3 from profile where openId = ?";
		
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openId);
			}
		}, new IProcessRS() {
			
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					int pro1 = rs.getInt("pro1");
					int pro2 = rs.getInt("pro2");
					int pro3 = rs.getInt("pro3");
					profile.setOpenId(openId);
					profile.setPro1(pro1);
					profile.setPro2(pro2);
					profile.setPro3(pro3);
				}
			}
		});
		return profile;
	}


	
	/**
	 * 根据用户浏览日志返回用户喜好
	 * @author bill
	 * @date   2018年6月9日 下午5:39:59
	 * @param openId
	 * @return
	 */
	public List<Integer> getProfileByLog(String openId) {
		String sql = "select sport_id, count(1) cou from news where news_id in("
				+ "SELECT news_id from log where openId = ? ) "
				+ "GROUP BY (sport_id) ORDER BY cou DESC";
		List<Integer> proList = new ArrayList<Integer>();
		
		db.excute_PSTquery(sql, new PsSetValues() {
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openId);
			}
		}, new IProcessRS() {
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Integer sport_id = rs.getInt("sport_id");
					proList.add(sport_id);
				}
			}
		});
		return proList;
	}


	/**
	 * 更新用户喜好
	 * @author bill
	 * @date   2018年6月17日 下午5:19:51
	 * @param openId
	 * @param list
	 */
	public void updateProfile(String openId, List<Integer> list) {
		String sql = "";
		Integer pro1 = list.size()>0?list.get(0):null;
		Integer pro2 = list.size()>1?list.get(1):null;
		Integer pro3 = list.size()>2?list.get(2):null;
		if(list.size()>2){
			sql = "update profile set pro1 = ?,pro2 = ?,pro3 = ? where openId = ?";
		}else if (list.size()>1) {
			sql = "update profile set pro1 = ?,pro2 = ?,pro3 = null where openId = ?";
		}else if (list.size()>0) {
			sql = "update profile set pro1 = ?,pro2 = null,pro3 = null where openId = ?";
		}else {
			sql = "update profile set pro1 = null,pro2 = null,pro3 = null where openId = ?";
		}
		db.excute_pst(sql, new PsSetValues() {
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				if(list.size()>2){
					pst.setInt(1, pro1);
					pst.setInt(2, pro2);
					pst.setInt(3, pro3);
					pst.setString(4, openId);
				}else if (list.size()>1) {
					pst.setInt(1, pro1);
					pst.setInt(2, pro2);
					pst.setString(3, openId);
				}else if (list.size()>0) {
					pst.setInt(1, pro1);
					pst.setString(2, openId);
				}else {
					pst.setString(1, openId);
				}
			}
		});
	}
}
