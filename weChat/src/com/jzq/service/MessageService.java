package com.jzq.service;

import java.util.List;

import com.jzq.dao.MessageDao;
import com.jzq.model.Message;

/**
 * 留言板
 * @author bill
 * @date   2018年6月17日 上午12:15:25
 */
public class MessageService {
	MessageDao messageDao = new MessageDao();

	/**
	 * 添加留言信息
	 * @author bill
	 * @date   2018年6月18日 上午12:16:35
	 * @param message
	 */
	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}

	/**
	 * 获取留言信息
	 * @author bill
	 * @date   2018年6月18日 上午12:32:39
	 * @param openId
	 * @return
	 */
	public List<Message> getMessage(String openId) {
		return messageDao.getMessage(openId);
	}
	
}
