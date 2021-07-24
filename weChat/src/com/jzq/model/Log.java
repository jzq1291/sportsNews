package com.jzq.model;

import java.util.Date;

/**
 * 用户浏览日志实体
 * @author bill
 * @date   2018年5月10日 上午10:20:29
 */
public class Log{
	
	private Integer news_id;
	private String openId;
	private Date time;
	
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(Integer news_id, String openId, Date time) {
		super();
		this.news_id = news_id;
		this.openId = openId;
		this.time = time;
	}

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Log [news_id=" + news_id + ", openId=" + openId + ", time="
				+ time + "]";
	}
	
}
