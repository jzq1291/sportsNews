package com.jzq.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;
import com.jzq.utils.PsSetValues;

/**
 * 日志相关
 * @author bill
 * @date   2018年6月8日 上午12:09:55
 */
public class LogDao {
	static JdbcUtils db = new JdbcUtils();
	
	/**
	 * 查询一个用户一个月内的日志量
	 * @author bill
	 * @date   2018年6月8日 上午12:57:31
	 * @param openid
	 * @return
	 */
	public int getLog_Num(String openid){
		List<Integer> num = new ArrayList<Integer>();
//		查询一个月以内的数据
		String sql = "select count(1) cou from log where openId = ?"
				+ " and DATE_SUB(CURDATE(), INTERVAL  30 Day) <= date(time)";
		
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openid);
			}
		}, new IProcessRS() {
			
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					num.add(rs.getInt("cou"));
				}
			}
		});
		if(num.size()!=0){
			return num.get(0);
		}else {
			return 0;
		}
	}
	
	
	
	/**
	 * 添加用户浏览日志
	 * @author bill
	 * @date   2018年6月8日 上午1:00:02
	 * @param openid
	 * @param news_id
	 */
	public void addLog(String openid,Integer news_id){
		String sql = "insert into log(news_id, openId, time) values(?,?,now())";
		db.excute_pst(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setInt(1, news_id);
				pst.setString(2,openid);
			}
		});
	}



	/**
	 * 最近十条日志
	 * @author bill
	 * @date   2018年6月17日 下午7:54:07
	 * @param openId
	 * @return
	 */
	public List<Integer> getRecentlyLog(String openId) {
		List<Integer> logs = new ArrayList<>();
		String sql = "select distinct(news_id) from log where openId = ? order by time desc limit 10";
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openId);
			}
		}, new IProcessRS() {
			
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					logs.add(rs.getInt("news_id"));
				}
			}
		});
		return logs;
	}
}
