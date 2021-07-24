package com.jzq.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jzq.model.Message;
import com.jzq.utils.IProcessRS;
import com.jzq.utils.JdbcUtils;
import com.jzq.utils.PsSetValues;

/**
 * 留言板
 * @author bill
 * @date   2018年6月18日 上午12:17:27
 */
public class MessageDao {
	static JdbcUtils db = new JdbcUtils();

	/**
	 * 添加留言
	 * @author bill
	 * @date   2018年6月18日 上午12:18:19
	 * @param message
	 */
	public void addMessage(Message message) {
		String sql = "insert into message (openId,time,content,email) values (?,now(),?,?)";
		db.excute_pst(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, message.getOpenId());
				pst.setString(2, message.getContent());
				pst.setString(3, message.getEmail());
			}
		});
	}

	
	/**
	 * 获取留言信息
	 * @author bill
	 * @date   2018年6月18日 上午12:33:28
	 * @param openId
	 * @return
	 */
	public List<Message> getMessage(String openId) {
		List<Message> messageList = new ArrayList<Message>();
		String sql = "select time,content,email from message where openId = ? order by time desc"; 
		db.excute_PSTquery(sql, new PsSetValues() {
			
			@Override
			public void setValue(PreparedStatement pst) throws SQLException {
				pst.setString(1, openId);
			}
		}, new IProcessRS() {
			@Override
			public void ProcessRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Date time = rs.getTimestamp("time");
					String content = rs.getString("content");
					String email = rs.getString("email");
					Message message = new Message(openId, time, content, email);
					messageList.add(message);
				}
			}
		});
		return messageList;
	}

}
