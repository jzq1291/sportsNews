package com.jzq.model;


/**
 * 运动类型实体
 * @author bill
 * @date   2018年5月10日 下午4:57:23
 */
public class Sports{

	private int sportId;
	private String sportName;
	private String description;
	private String display;

	public Sports() {
	}
	
	public Sports(int sportId) {
		this.sportId = sportId;
	}
	
	public Sports(int sportId, String sportName, String description,
			String display) {
		super();
		this.sportId = sportId;
		this.sportName = sportName;
		this.description = description;
		this.display = display;
	}

	public int getSportId() {
		return this.sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public String getSportName() {
		return this.sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return "Sports [sportId=" + sportId + ", sportName=" + sportName
				+ ", description=" + description + ", display=" + display + "]";
	}

}
