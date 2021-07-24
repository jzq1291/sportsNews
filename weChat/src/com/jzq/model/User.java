package com.jzq.model;

// Generated 2018-5-10 10:17:34 by Hibernate Tools 3.4.0.CR1


/**
 * 用户实体
 * @author bill
 * @date   2018年5月10日 下午4:57:08
 */
public class User{

	private String openId;
	private String nickName;
	private String avatarUrl;
	private String country;
	private String province;
	private String city;

	public User() {
	}

	public User(String openId) {
		super();
		this.openId = openId;
	}

	public User(String openId, String nickName, String avatarUrl,
			String country, String province, String city) {
		super();
		this.openId = openId;
		this.nickName = nickName;
		this.avatarUrl = avatarUrl;
		this.country = country;
		this.province = province;
		this.city = city;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [openId=" + openId + ", nickName=" + nickName
				+ ", country=" + country + ", province=" + province + ", city="
				+ city + "]";
	}
	
}
