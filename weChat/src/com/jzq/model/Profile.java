package com.jzq.model;


/**
 * 用户喜好实体
 * @author bill
 * @date   2018年5月10日 下午4:57:43
 */
public class Profile{

	private String openId;
	private Integer pro1;
	private Integer pro2;
	private Integer pro3;

	public Profile() {
	}

	public Profile(String openId) {
		this.openId = openId;
	}

	public Profile(String openId, Integer pro1, Integer pro2, Integer pro3) {
		super();
		this.openId = openId;
		this.pro1 = pro1;
		this.pro2 = pro2;
		this.pro3 = pro3;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getPro1() {
		return pro1;
	}

	public void setPro1(Integer pro1) {
		this.pro1 = pro1;
	}

	public Integer getPro2() {
		return pro2;
	}

	public void setPro2(Integer pro2) {
		this.pro2 = pro2;
	}

	public Integer getPro3() {
		return pro3;
	}

	public void setPro3(Integer pro3) {
		this.pro3 = pro3;
	}
	
	
}
